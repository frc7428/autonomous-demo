/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.ParallelRaceGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.robot.commands.CommandA;
import frc.robot.commands.CommandB;
import frc.robot.subsystems.FirstSubsystem;
import frc.robot.subsystems.SecondSubsystem;

/**
 * This class is where the bulk of the robot should be declared. Since
 * Command-based is a "declarative" paradigm, very little robot logic should
 * actually be handled in the {@link Robot} periodic methods (other than the
 * scheduler calls). Instead, the structure of the robot (including subsystems,
 * commands, and button mappings) should be declared here.
 */
public class RobotContainer {
  // The robot's subsystems and commands are defined here...
  private final FirstSubsystem mFirstSubsystem = new FirstSubsystem();
  private final SecondSubsystem mSecondSubsystem = new SecondSubsystem();

  private final CommandA mCommandA = new CommandA(mFirstSubsystem);
  private final CommandB mCommandB = new CommandB(mSecondSubsystem);

  /**
   * The container for the robot. Contains subsystems, OI devices, and commands.
   */
  public RobotContainer() {
    // Configure the button bindings
    configureButtonBindings();
  }

  /**
   * Use this method to define your button->command mappings. Buttons can be
   * created by instantiating a {@link GenericHID} or one of its subclasses
   * ({@link edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then
   * passing it to a {@link edu.wpi.first.wpilibj2.command.button.JoystickButton}.
   */
  private void configureButtonBindings() {
  }

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    // Run command A for five seconds,
    // then wait for three seconds,
    // then run command B for two seconds,
    // then wait for four seconds,
    // then run command A and command B together for six seconds.
    return new ParallelRaceGroup(mCommandA, new WaitCommand(5))
      .andThen(new WaitCommand(3))
      .andThen(new ParallelRaceGroup(mCommandB, new WaitCommand(2)))
      .andThen(new WaitCommand(4))
      .andThen(new ParallelRaceGroup(mCommandA, mCommandB, new WaitCommand(6)));
  }
}
