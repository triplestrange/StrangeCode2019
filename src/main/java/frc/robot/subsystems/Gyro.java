package frc.robot.subsystems;

import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.PIDSource;
import edu.wpi.first.wpilibj.PIDSourceType;
import edu.wpi.first.wpilibj.SPI;

public class Gyro implements PIDSource{
    private static AHRS gyro = new AHRS(SPI.Port.kMXP);
    private static double offset = 0;

    public Gyro() {
        super();
    }

    public double getAngle() {
        return gyro.getAngle() + offset;
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
    // public static double angleCorrect() {
    // return gyro.getAngle() * -.025;
    // }

    @Override
    public void setPIDSourceType(PIDSourceType pidSource) {

    }

    @Override
    public PIDSourceType getPIDSourceType() {
        return null;
	}

    // public double straight(boolean angle) {
    // if (angle) {
    // currentangle = gyro.getAngle();
    // SwerveDrive.angle = false;
    // }
    // return (gyro.getAngle() - currentangle) * .015;
    // }
}