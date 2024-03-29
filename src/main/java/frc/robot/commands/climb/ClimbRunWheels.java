package frc.robot.commands.climb;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.OI;
import frc.robot.Robot;
import frc.robot.RobotMap;

public class ClimbRunWheels extends Command {
  public ClimbRunWheels() {
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
  }

  // Called just before this Command runs the first time
  @Override
    protected void initialize() {
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
    protected void execute() {
      Robot.stilt.runWheels(OI.joy1.getRawAxis(RobotMap.Controller.RY));
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return false;
  }

  // Called once after isFinished returns true
  @Override
    protected void end() {
        Robot.stilt.stopWheels();
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
    protected void interrupted() {
        Robot.stilt.stopWheels();
  }
}
