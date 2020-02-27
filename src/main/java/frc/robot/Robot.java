/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.networktables.NetworkTableInstance;
//Commands & Subsystems
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import edu.wpi.first.wpilibj2.command.button.Button;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import frc.robot.subsystems.*;
import frc.robot.commands.*;
//Individual imports
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;



public class Robot extends TimedRobot {
  private Command m_autonomousCommand;
  private RobotContainer m_robotContainer;
  DriveSubsystem drive_subsystem;
  //CameraSubsystem camera_subsystem;
  EncoderSubsystem encoder_subsystem;
  OI oi;
  TurretSubsystem turret_subsystem;
  ShootingCommand shooting_command;
  IntakeSubsystem intake_subsystem;
  double turretVal;
  double turretVal2;
  private boolean m_LimelightHasValidTarget = false;
  private double m_LimelightDriveCommand = 0.0;
  private double m_LimelightSteerCommand = 0.0;

  JoystickButton btn;
  DriveForward drive_forward;
  DriveBackward drive_backward;
  Limelight turret_Limelight;
  
 

  @Override
  public void robotInit() {
    m_robotContainer = new RobotContainer();
    drive_subsystem = new DriveSubsystem();
    //camera_subsystem = new CameraSubsystem();
    encoder_subsystem = new EncoderSubsystem();
    turret_subsystem = new TurretSubsystem();
    intake_subsystem = new IntakeSubsystem();
    turret_Limelight = new Limelight("Turret");
    oi = new OI();
    btn = new JoystickButton(oi.getController(), 5);
   


    //settings when the robot turns on
    
  }

  /**
   * This function is called every robot packet, no matter the mode. Use this for items like
   * diagnostics that you want ran during disabled, autonomous, teleoperated and test.
   *
   * <p>This runs after the mode specific periodic functions, but before
   * LiveWindow and SmartDashboard integrated updating.
   */
  @Override
  public void robotPeriodic() {
    // Runs the Scheduler.  This is responsible for polling buttons, adding newly-scheduled
    // commands, running already-scheduled commands, removing finished or interrupted commands,
    // and running subsystem periodic() methods.  This must be called from the robot's periodic
    // block in order for anything in the Command-based framework to work.
    CommandScheduler.getInstance().run();
  }

  /**
   * This function is called once each time the robot enters Disabled mode.
   */
  @Override
  public void disabledInit() {
  }

  @Override
  public void disabledPeriodic() {
  }

  /**
   * This autonomous runs the autonomous command selected by your {@link RobotContainer} class.
   */
  @Override
  public void autonomousInit() {
    m_autonomousCommand = m_robotContainer.getAutonomousCommand();

    // schedule the autonomous command (example)
    if (m_autonomousCommand != null) {
      m_autonomousCommand.schedule();
    }
    // some autonomus to test in the future
    // new DriveForward(drive_subsystem, encoder_subsystem, 2);
    // new DriveBackward(drive_subsystem, encoder_subsystem, 4);
  }

  /**
   * This function is called periodically during autonomous.
   */
  @Override
  public void autonomousPeriodic() {
    double leftAdjust = -1.0; 
    double rightAdjust = -1.0; // default speed values for chase
    double mindistance = 5;
    leftAdjust -= turret_Limelight.steeringAdjust();//adjust each side according to tx
    rightAdjust += turret_Limelight.steeringAdjust();
/*
     if(Math.abs(camera_subsystem.getTy()) <= mindistance){//checks if the height is less than five, if it is stop 
       drive_subsystem.tankDrive(0, 0, 1);
     }else{
       if(camera_subsystem.isTarget() == false){//check if there is target, if not, spin
         drive_subsystem.tankDrive(-.5, .5, .5);
       }else if((camera_subsystem.isTarget() == true)){//check if there is target, use adjust values to move
         drive_subsystem.tankDrive(leftAdjust, rightAdjust, 0.5);
         }
     }
  }
*/}
  @Override
  public void teleopInit() {
    // This makes sure that the autonomous stops running when
    // teleop starts running. If you want the autonomous to
    // continue until interrupted by another command, remove
    // this line or comment it out.
    if (m_autonomousCommand != null) {
      m_autonomousCommand.cancel();
    }
    //camera_subsystem.ledOff();
    boolean m_LimelightHasValidTarget;

    btn.whenPressed(new ShootingCommand(turret_subsystem, oi, 0.8));
  }

  /**
   * This function is called periodically during operator control.
   */
  @Override
  public void teleopPeriodic() {
    drive_subsystem.tankDrive(oi.getLeftStick(), oi.getRightStick(), 0.95);
    drive_subsystem.getYaw();
    //drive_subsystem.tankDrive(oi.getLeftStick(), oi.getRightStick(),1);
    //print("Encoder position is"+encoder_subsystem.getPosition());
    //print("Encoder velocity is"+encoder_subsystem.getVelocity());
    print("encoder pos is" + turret_subsystem.encoderVal());
    turretVal = oi.getLeftTurretAxis();//Get fixed inputs from oi
    turretVal2 = oi.getRightTurretAxis();
    /*
    if (turret_subsystem.encoderVal()>=8000){//If the encoder value is greater than 8000, do this
      while ((turret_subsystem.encoderVal()>=8000)&&(turretVal2>=.1)){// if the value is above 8000, and trying to turn right
        turretVal2 = 0;//reduce right input
        print("Above 8000 enc value, turret is"+turretVal2);
      }
    }
    if (turret_subsystem.encoderVal()<=-8000){
      while ((turret_subsystem.encoderVal()<=-8000)&&(turretVal2<=-.1)){
        turretVal = 0;//reduce left input
        print("Below -8000 enc value, turret is"+turretVal);
      }
    }
    */
    turretVal2 = turretVal-turretVal2;//final calculations
    turret_subsystem.setTurretSpeed(turretVal2, 0.25);

    //Autoaim (toggle)
    if (oi.circle()==true){
      while(oi.circleup()!=true){
        if (turret_Limelight.canSeeTarget()==false){
          //if there is no target, do nothing
        }else if((turret_Limelight.canSeeTarget()==true)){
          double adjust = turret_Limelight.steeringAdjust();//if there is a target, get the distance from it
          turret_subsystem.setTurretSpeed(adjust, 0.25);//set the speed to that distance, left is negative and right is positive
        }
      }
    }

    turret_subsystem.feeder(oi.r1());
    turret_subsystem.encoderReset(oi.triangle());
    intake_subsystem.setFloorSpeed(-oi.square());
    intake_subsystem.setIntakeSpeed(-oi.x());
    encoder_subsystem.getPosition();
    encoder_subsystem.getVelocity();
    // turret_subsystem.shooterEncoder();

  }

  @Override
  public void testInit() {
    // Cancels all running commands at the start of test mode.
    CommandScheduler.getInstance().cancelAll();
  }

  /**
   * This function is called periodically during test mode.
   */
  @Override
  public void testPeriodic() {
  }
  //Other functions

  public void print(String value){
    System.out.println(value);
  }
  
}
