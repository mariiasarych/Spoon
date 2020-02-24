package frc.robot.subsystems;

import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.networktables.NetworkTable;

public class Limelight extends SubsystemBase {

    private NetworkTable table;

    public Limelight(String limelightName) {
        table = NetworkTableInstance.getDefault().getTable(limelightName);
    }

    public boolean canSeeTarget() {
        return table.getEntry("tv").getDouble(0) == 1;
    }

    public double offsetX() {
        return table.getEntry("tx").getDouble(0.00);
    }

    public double offsetY() {
        return table.getEntry("ty").getDouble(0.00);
    }

    public double steeringAdjust() {
        float kp = -.05f;//Adjusts the value returned from Limelight
        float minCommand = .005f;//Minimum value a value can have
        float steeringAdjust = 0.05f;//Default value of adjust
        float tx = (float)offsetX();
        //SmartDashboard.setDefaultNumber("TX", tx);
        float headingError = -tx;
      
        if(tx > 1) {
            steeringAdjust = kp*headingError -minCommand;
        }else if (tx < 1){
            steeringAdjust = kp*headingError + minCommand;
        }
        return steeringAdjust;
      }

      public double distanceAdjust(){
        float KpDist = -0.1f;
        float Xoffset = (float)offsetY();
        float distance_error = -Xoffset;
        float distance_adjust = distance_error*KpDist;
        if (distance_adjust > 1){
          distance_adjust = 1;
        }else if(distance_adjust <-1){
          distance_adjust = -1;
        }
        return distance_adjust;
      }

}