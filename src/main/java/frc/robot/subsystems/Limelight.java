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

}