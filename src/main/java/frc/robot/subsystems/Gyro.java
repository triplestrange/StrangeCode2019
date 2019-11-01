package frc.robot.subsystems;

import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.PIDSource;
import edu.wpi.first.wpilibj.PIDSourceType;
import edu.wpi.first.wpilibj.SPI;

public class Gyro implements PIDSource{
    private static AHRS gyro = new AHRS(SPI.Port.kMXP);

    //Offset to start the robot facing on the right
    //TODO make a selector on the Smart Dashboard for which direction we are facing
    private static double offset = 0;

    public Gyro() {
        super();
    }

    public double getAngle() {
        return gyro.getAngle() + offset;
    }

    public double getTilt() {
        return (double)gyro.getPitch()-3.3;
    }

    public double getRate() {
        return gyro.getRate();
    }

    public void reset() {
        gyro.zeroYaw();
        offset = 0;
    }

    public void set(double angle) {
        gyro.reset();
        offset = angle;
    }

    public double pidGet() {
        return getAngle();
    }

    @Override
    public void setPIDSourceType(PIDSourceType pidSource) {}

    @Override
    public PIDSourceType getPIDSourceType() {return null;}
}