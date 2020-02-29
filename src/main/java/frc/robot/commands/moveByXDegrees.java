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
  double i_destinationAngle;
  double maxAngle;
  double minAngle;
  double angle_input;
  double i_intialangle;
  /**
   * Creates a new moveByXDegrees.
   */
  public moveByXDegrees(TurretSubsystem subsystem,double d_angle) {
    turret_subsystem = subsystem; 
    d_angle = i_destinationAngle;
    addRequirements(turret_subsystem);
    // Use addRequirements() here to declare subsystem dependencies.
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    i_intialangle = turret_subsystem.encoderVal();
    initialAngle = ((((angle_input + 10000)* 180)/20000) - 90);
    
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    destinationAngle = (i_destinationAngle + initialAngle);
    if (destinationAngle <= -72) {
      destinationAngle = -72;
    }
    else if (destinationAngle >= 72){
      destinationAngle = 72;
    }
    
    maxAngle = 72; 
    minAngle = -72;
    angle_input = turret_subsystem.encoderVal();
    currentAngle = ((((angle_input + 10000)* 180)/20000) - 90);

    if (currentAngle < destinationAngle) {
      turret_subsystem.setTurretSpeed(1, .25);
    }
    else if (currentAngle > destinationAngle) {
      turret_subsystem.setTurretSpeed(-1, .25);
    }
   
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    turret_subsystem.setTurretSpeed(0, .25);
    System.out.print(currentAngle);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
      return (currentAngle == destinationAngle);
    
  }
}
