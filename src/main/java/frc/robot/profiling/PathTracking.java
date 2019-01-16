package frc.robot.profiling;

import java.io.FileNotFoundException;
import java.io.PrintWriter;

import frc.robot.subsystems.Gyro;
import frc.robot.subsystems.SwerveDrive;
import frc.robot.subsystems.SwerveModule;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import jaci.pathfinder.Pathfinder;

public class PathTracking {

    double currentX, currentY, currentZ;
    public double[] drive;
    public double[] lastSteer;
    double timeIter;
    SwerveDrive swerve;
    Gyro gyro;
    PrintWriter writer;

    public PathTracking(SwerveDrive modules) {
        this.swerve = modules;
        drive = new double[swerve.modules.length];
        lastSteer = new double[swerve.modules.length];
        // try {
        // //writer = new PrintWriter("robot.txt");
        // } catch (FileNotFoundException e) {
        // // TODO Auto-generated catch block
        // e.printStackTrace();
        // }
    }

    public void reset() {
        for (int i = 0; i < swerve.modules.length; i++) {
            drive[i] = 0;
            swerve.modules[i].resetEncoder();
            currentX = 0;
            currentY = 0;
        }
    }

    public void update() {
        double xAvg = 0;
        double yAvg = 0;
        for (int i = 0; i < swerve.modules.length; i++) {
            double dEncoder = swerve.modules[i].getDistance();
            double sEncoder = swerve.modules[i].getAngle();
            double deltaSteer = Pathfinder.boundHalfDegrees(Pathfinder.r2d(sEncoder - lastSteer[i]));
            double deltaDrive = dEncoder - drive[i] + deltaSteer / 360 * 12288 * SwerveModule.distPerPulse;

            double dx = -(deltaDrive) * Math.sin(sEncoder)
                    - swerve.modules[i].positionY * (Math.toRadians(gyro.getAngle() - currentZ));
            double dy = (deltaDrive) * Math.cos(sEncoder)
                    + swerve.modules[i].positionX * (Math.toRadians(gyro.getAngle() - currentZ));
            xAvg = xAvg + dx / swerve.modules.length;
            yAvg = yAvg + dy / swerve.modules.length;
            drive[i] = dEncoder;
            lastSteer[i] = sEncoder;
        }
        currentZ = gyro.getAngle();
        currentX = currentX + xAvg * Math.cos(Math.toRadians(currentZ)) + yAvg * Math.sin(Math.toRadians(currentZ));
        currentY = currentY - xAvg * Math.sin(Math.toRadians(currentZ)) + yAvg * Math.cos(Math.toRadians(currentZ));

        // writer.println(currentX+" "+currentY);
        SmartDashboard.putNumber("CurrentX", currentX);
        SmartDashboard.putNumber("CurrentY", currentY);
        SmartDashboard.putNumber("CurrentZ", currentZ);
    }
    // public void close() {
    // writer.close();
    // }
}