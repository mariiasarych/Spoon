/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.TurretSubsystem;

public class moveByXDegrees extends CommandBase {
  TurretSubsystem turret_subsystem;
  double initialAngle;
  double currentAngle;
  double destinationAngle;
  double maxAngle;
  double minAngle;
  /**
   * Creates a new moveByXDegrees.
   */
  public moveByXDegrees(TurretSubsystem subsystem) {
    turret_subsystem = subsystem; 
    addRequirements(turret_subsystem);
    // Use addRequirements() here to declare subsystem dependencies.
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    initialAngle = turret_subsystem.encoderVal();
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    maxAngle = 90; //encoder's values are not yet transformed to degrees
    minAngle = -90; //encoder's values are not yet transformed to degrees

    currentAngle = turret_subsystem.encoderVal();

    if (currentAngle - initialAngle < destinationAngle && currentAngle <= maxAngle) {
      turret_subsystem.setTurretSpeed(1);
    }
    else if (currentAngle -initialAngle > destinationAngle && currentAngle >= minAngle) {
      turret_subsystem.setTurretSpeed(-1);
    }
   
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    turret_subsystem.setTurretSpeed(0);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return (currentAngle - initialAngle == destinationAngle);
    
    
  }
}
