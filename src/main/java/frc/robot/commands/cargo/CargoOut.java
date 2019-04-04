package frc.robot.commands.cargo;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class CargoOut extends CommandGroup {
  public CargoOut() {
        addParallel(new CargoIntakeOut());
        addParallel(new CargoHandlerOut());
        addParallel(new CargoLongRetract());
  }
}
