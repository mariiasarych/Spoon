// /*----------------------------------------------------------------------------*/
// /* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
// /* Open Source Software - may be modified and shared by FRC teams. The code   */
// /* must be accompanied by the FIRST BSD license file in the root directory of */
// /* the project.                                                               */
// /*----------------------------------------------------------------------------*/

// package frc.robot.commands;

// import edu.wpi.first.wpilibj2.command.*;
// import frc.robot.commands.*;
// import frc.robot.subsystems.*;
// import frc.robot.RobotContainer;

// /**
//  * Add your docs here.
//  */
// public class AutoTest {
//     public Command autonomous1(
//         TurretSubsystem turret_subsystem,
//         EncoderSubsystem encoder_subsystem,
//         IntakeSubsystem intake_subsystem
//     ) {
//         return new SequentialCommandGroup(
//             new DriveForward(RobotContainer.m_drive_subsystem, encoder_subsystem, 2),
//             new DriveBackward(RobotContainer.m_drive_subsystem, encoder_subsystem, 4),
//             new TurnLeft(RobotContainer.m_drive_subsystem),
//             new InstantCommand(() -> intake_subsystem.setIntakeSpeed(1.0))
//         );
//     }

//     public Command autonomous2() {
//         return new InstantCommand(() -> RobotContainer.m_exampleSubsystem.test());
//         // return new SequentialCommandGroup(
//         //     new DriveForward(drive_subsystem, encoder_subsystem, 2),
//         //     new DriveBackward(drive_subsystem, encoder_subsystem, 4),
//         //     new TurnLeft(drive_subsystem),
//         //     new InstantCommand(() -> intake_subsystem.setIntakeSpeed(1.0))
//         // );
//     }
// }

