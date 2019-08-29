package frc.robot.commands.cargo;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class CargoIn extends CommandGroup {
  public CargoIn() {
        addParallel(new CargoIntake(1));
        addParallel(new CargoHandlerIn());
        addParallel(new CargoLongExtend());
  }
}
