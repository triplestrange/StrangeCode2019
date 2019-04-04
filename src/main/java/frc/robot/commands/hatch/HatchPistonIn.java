package frc.robot.commands.hatch;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class HatchPistonIn extends Command {
  public HatchPistonIn() {
    requires(Robot.hatch);
  }

  @Override
    protected void initialize() {
      Robot.hatch.place();
  }

  @Override
  protected void execute() {
  }

  @Override
  protected boolean isFinished() {
    return true;
  }

  @Override
  protected void end() {
  }

  @Override
  protected void interrupted() {
      
  }
}