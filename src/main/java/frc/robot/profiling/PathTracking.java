package frc.robot.profiling;

import frc.robot.Robot;
import frc.robot.subsystems.SwerveModule;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import jaci.pathfinder.Pathfinder;

public class PathTracking {

    double currentX, currentY, currentZ;
    public double[] drive;
    public double[] lastSteer;
    double timeIter;

    public PathTracking() {
        drive = new double[Robot.swerve.modules.length];
        lastSteer = new double[Robot.swerve.modules.length];
    }

    public void reset() {
        for (int i = 0; i < Robot.swerve.modules.length; i++) {
            drive[i] = 0;
            Robot.swerve.modules[i].resetEncoder();
            currentX = 0;
            currentY = 0;
        }
    }

    public void setCoords(double currentX, double currentY) {
        this.currentX = currentX;
        this.currentY = currentY;
    }
    public void update() {
        double xAvg = 0;
        double yAvg = 0;
        for (int i = 0; i < Robot.swerve.modules.length; i++) {
            double dEncoder = Robot.swerve.modules[i].getDistance();
            double sEncoder = Robot.swerve.modules[i].getAngle();
            double deltaSteer = Pathfinder.boundHalfDegrees(Pathfinder.r2d(sEncoder - lastSteer[i]));
            double deltaDrive = dEncoder - drive[i] + deltaSteer / 360 * 3 * SwerveModule.distPerRev;

            double dx = -(deltaDrive) * Math.sin(sEncoder)
                    - Robot.swerve.modules[i].positionY * (Math.toRadians(Robot.navxGyro.getAngle() - currentZ));
            double dy = (deltaDrive) * Math.cos(sEncoder)
                    + Robot.swerve.modules[i].positionX * (Math.toRadians(Robot.navxGyro.getAngle() - currentZ));
            xAvg = xAvg + dx / Robot.swerve.modules.length;
            yAvg = yAvg + dy / Robot.swerve.modules.length;
            drive[i] = dEncoder;
            lastSteer[i] = sEncoder;
        }
        currentZ = Robot.navxGyro.getAngle();
        currentX = currentX + xAvg * Math.cos(Math.toRadians(currentZ)) + yAvg * Math.sin(Math.toRadians(currentZ));
        currentY = currentY - xAvg * Math.sin(Math.toRadians(currentZ)) + yAvg * Math.cos(Math.toRadians(currentZ));

        SmartDashboard.putNumber("CurrentX", currentX);
        SmartDashboard.putNumber("CurrentY", currentY);
        SmartDashboard.putNumber("CurrentZ", currentZ);
    }
}