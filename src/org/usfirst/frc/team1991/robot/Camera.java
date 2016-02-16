package org.usfirst.frc.team1991.robot;

import com.ni.vision.NIVision;
import com.ni.vision.NIVision.Image;

import edu.wpi.first.wpilibj.CameraServer;

public class Camera {
  int session = -1;
  String cam = "cam0";
  Image frame;

  public Camera() {
    frame = NIVision.imaqCreateImage(NIVision.ImageType.IMAGE_RGB, 0);
  }

  public void startCamera(String newCam) {
    session = NIVision.IMAQdxOpenCamera(newCam, NIVision.IMAQdxCameraControlMode.CameraControlModeController);
    NIVision.IMAQdxConfigureGrab(session);
    NIVision.IMAQdxStartAcquisition(session);
    cam = newCam;
  }

  public void stopCamera() {
    if (session != -1) {
      NIVision.IMAQdxStopAcquisition(session);
      NIVision.IMAQdxCloseCamera(session);
    }
  }

  public void refreshFrame() {
    NIVision.IMAQdxGrab(session, frame, 1);
    CameraServer.getInstance().setImage(frame);
  }
}
