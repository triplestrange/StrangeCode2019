package frc.robot.profiling;

import frc.robot.Robot;
import frc.robot.util.PIDSourceAdapter;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import jaci.pathfinder.Trajectory;

public class PathFollower {
    ProfileFollower xProfile;
    ProfileFollower yProfile;
    ProfileFollower zProfile;
    ProfileFollower parProfile, perpProfile;
    double xPower, yPower, zPower;
    double parPower, perpPower;
    SwerveTrajectory currentTrajectory;
    long startTime;
    double kP = 0.0007;
    double kD = 0.00005;

    public PathFollower() {
        this.zProfile = new ProfileFollower(.002, 0, 0.02, 0, 0.002, new PIDSourceAdapter(() -> Robot.path.currentZ),
                (z) -> zPower = z);
        this.parProfile = new ProfileFollower(.008, 0.005, 0.1, 0, 0.01, new PIDSourceAdapter(() -> 0.0),
                (y) -> parPower = y);
        this.perpProfile = new ProfileFollower(0, 0, 0.05, 0, 0.0, new PIDSourceAdapter(() -> 0.0),
                (x) -> perpPower = x);
        if (!SmartDashboard.containsKey("zP"))
            SmartDashboard.putNumber("zP", zProfile.kP);
        if (!SmartDashboard.containsKey("zD"))
            SmartDashboard.putNumber("zD", zProfile.kD);
        if (!SmartDashboard.containsKey("zV"))
            SmartDashboard.putNumber("zV", zProfile.kV);
        if (!SmartDashboard.containsKey("parP"))
            SmartDashboard.putNumber("parP", parProfile.kP);
        if (!SmartDashboard.containsKey("parD"))
            SmartDashboard.putNumber("parD", parProfile.kD);
        if (!SmartDashboard.containsKey("perpP"))
            SmartDashboard.putNumber("perpP", perpProfile.kP);
        if (!SmartDashboard.containsKey("perpD"))
            SmartDashboard.putNumber("perpD", perpProfile.kD);
    }

    private double totalTime(Trajectory t) {
        return t.get(0).dt * (t.length() - 1);
    }

    public Trajectory.Segment currentSegment(double time) {
        if (currentTrajectory == null)
            return null;
        try {
            return currentTrajectory
                    .get((int) Math.round(time / totalTime(currentTrajectory) * currentTrajectory.length()));
        } catch (IndexOutOfBoundsException e) {
            return currentTrajectory.get(currentTrajectory.length() - 1);
        }
    }

    public void startTrajectory(SwerveTrajectory t) {
        zProfile.kP = SmartDashboard.getNumber("zP", zProfile.kP);
        zProfile.kD = SmartDashboard.getNumber("zD", zProfile.kD);
        zProfile.kV = SmartDashboard.getNumber("zV", zProfile.kV);
        parProfile.kP = SmartDashboard.getNumber("parP", parProfile.kP);
        parProfile.kD = SmartDashboard.getNumber("parD", parProfile.kD);
        perpProfile.kP = SmartDashboard.getNumber("perpP", perpProfile.kP);
        perpProfile.kD = SmartDashboard.getNumber("perpD", perpProfile.kD);
        currentTrajectory = t;
        startTime = System.nanoTime();

        parProfile.startProfile(new MotionProfile() {
            public double currentP(double t) {
                return (currentSegment(t).x - Robot.path.currentX) * Math.cos(currentSegment(t).heading)
                        + (currentSegment(t).y - Robot.path.currentY) * Math.sin(currentSegment(t).heading);
            }

            public double currentV(double t) {
                Trajectory.Segment cs = currentSegment(t);
                return cs.velocity;
            }

            public double totalTime() {
                return PathFollower.this.totalTime(currentTrajectory);
            }

            public double currentA(double t) {
                Trajectory.Segment cs = currentSegment(t);
                return cs.acceleration;
            }
        });
        perpProfile.startProfile(new MotionProfile() {
            public double currentP(double t) {
                return -(currentSegment(t).x - Robot.path.currentX) * Math.sin(currentSegment(t).heading)
                        + (currentSegment(t).y - Robot.path.currentY) * Math.cos(currentSegment(t).heading);
            }

            public double currentV(double t) {
                return 0.0;
            }

            public double totalTime() {
                return PathFollower.this.totalTime(currentTrajectory);
            }

            public double currentA(double t) {
                return 0.0;
            }
        });
        zProfile.startProfile(new MotionProfile() {
            int currentWaypoint = 0;

            public double currentP(double t) {
                if (t >= currentTrajectory.waypointTime[currentWaypoint + 1]
                        && currentWaypoint < currentTrajectory.waypoints.length - 2) {
                    currentWaypoint = currentWaypoint + 1;
                }
                double angle = currentTrajectory.gyroProfile[currentWaypoint]
                        .currentP(t - currentTrajectory.waypointTime[currentWaypoint]);

                SmartDashboard.putNumber("zProfileTargetP", angle);
                return angle;
            }

            public double currentV(double t) {
                return currentTrajectory.gyroProfile[currentWaypoint]
                        .currentV(t - currentTrajectory.waypointTime[currentWaypoint]);
            }

            public double totalTime() {
                return PathFollower.this.totalTime(currentTrajectory);
            }

            public double currentA(double t) {
                return 0;
            }
        });
    }

    public void update() {
        if (isFinished()) {
            Robot.swerve.driveField(0, 0, 0);
            return;
        } else {
            double currentTime = (System.nanoTime() - startTime) / 1e9;
            zProfile.update();
            parProfile.update();
            perpProfile.update();
            yPower = perpPower * Math.cos(currentSegment(currentTime).heading)
                    + parPower * Math.sin(currentSegment(currentTime).heading);
            xPower = -perpPower * Math.sin(currentSegment(currentTime).heading)
                    + parPower * Math.cos(currentSegment(currentTime).heading);
            Robot.swerve.driveField(xPower, yPower, zPower);
        }
    }

    public void cancel() {
        currentTrajectory = null;
        zProfile.cancel();
        parProfile.cancel();
        perpProfile.cancel();
    }

    public boolean isFinished() {
        if (currentTrajectory == null) {
            return true;
        } else if (parProfile.isFinished() && perpProfile.isFinished() && zProfile.isFinished()) {
            return true;
        } else {
            return false;
        }
    }
}
