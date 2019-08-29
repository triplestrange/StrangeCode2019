package frc.robot.commands.cargo;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class CargoOut extends CommandGroup {
  public CargoOut() {
        addParallel(new CargoIntake(-1));
        addParallel(new CargoHandlerOut());
        addParallel(new CargoLongRetract());
  }
}
