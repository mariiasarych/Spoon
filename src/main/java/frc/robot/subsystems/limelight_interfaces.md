# Directions for multiple limelight use.
After configuring each limelight in the UI, this should simplify using multiple limelights at once ine the code.

This is where we setup our limelight in the code, we could utilize this in our commands for automatically moving the turret.
```
Limelight shooterLimelight = new Limelight(...);
```

We could utilize this one for tracking the intake of the balls as per our robot design.
```
Limelight feederLimelight = new Limelight(...);
```

# Limelight Subsystem
The limelight subsystem should implement modes of operations and methods for swapping between modes and getting values depending on the current mode.

I read this documentation to put together this guide: https://readthedocs.org/projects/limelight/downloads/pdf/latest/

The goal is to simplify the limelight's network tables controls to the ideal set of controls for our multiple use cases.

## Constructor
Should require a name for the limelight device that separates each device from the other. Also require any basic configuration required, but I doubt there are any other configurations required here.

## Methods
**get current mode**: This should return the current mode, via an Enum

**start _(insert mode name here) mode**: There should be a method here for each mode. For example the Vision mode requires that you give it a pipeline.

### These methods should work during vision mode. Otherwise they should return null;

**canSeeTarget**: This method should return whether there is a value target or not. (**tv** in network tables).

**horizontalOffset**: This method should return the horizontal offest. (**tx** in network tables)

**verticalOffset**: This method should return the vertical offset. (**ty** in network tables)

## Modes
### Resting
**Goal**: Switch to a low powered state. Important for when the camera is not in use.

### Vision Mode
**Goal**: Get ready for watching the target, including automatically turning on lights + setting the pipeline in use.

### Vision Idle Mode
**Goal**: This mode is for if switching to resting is too expensive, so this is a middle ground to quickly shut off and down vision mode.

### Drive Quality Mode
**Goal**: Camera should swap to drive mode, which should be ideal for a human watching the screen
