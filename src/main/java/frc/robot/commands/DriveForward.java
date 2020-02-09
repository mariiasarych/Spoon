/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.DriveSubsystem;
import frc.robot.subsystems.EncoderSubsystem;

public class DriveForward extends CommandBase {
  DriveSubsystem drive_subsystem;
  EncoderSubsystem encoder_subsysem;

  public DriveForward(DriveSubsystem subsystem, EncoderSubsystem subsystem2) {
    drive_subsystem = subsystem;
    encoder_subsysem = subsystem2;
    addRequirements(drive_subsystem);
    addRequirements(encoder_subsysem);
   //add distance requirement, work with the values first
  }

  // Called just before this Command runs the first time
  @Override
  public void initialize() {
    //get the initial value of the encoder
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  public void execute() {
//set motors to 1
//check for curent encoder val
  }

  // Called once after isFinished returns true
  @Override
  public void end(boolean interrupted) {
  }
  
  // Make this return true when this Command no longer needs to run execute()
  @Override
  public boolean isFinished() {
    return false;
  }
}
