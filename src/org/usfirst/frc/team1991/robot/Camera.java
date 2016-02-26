package org.usfirst.frc.team1991.robot;

import com.ni.vision.NIVision.Image;
import com.ni.vision.NIVision;

import edu.wpi.first.wpilibj.CameraServer;

public class Camera {
	private int session = -1; // Assume -1 means no camera
	private String camName;
	private Image frame;

	public Camera() {
		frame = NIVision.imaqCreateImage(NIVision.ImageType.IMAGE_RGB, 0);
	}

	public void start(String camName) {
		if (!getActive()) {
			session = NIVision.IMAQdxOpenCamera(camName, NIVision.IMAQdxCameraControlMode.CameraControlModeController);
			NIVision.IMAQdxConfigureGrab(session);
			NIVision.IMAQdxStartAcquisition(session);
			this.camName = camName;
		}
	}

	public void stop() {
		if (getActive()) {
			NIVision.IMAQdxStopAcquisition(session);
			NIVision.IMAQdxCloseCamera(session);
			session = -1;
		}
	}

	public void refreshFrame() {
		NIVision.IMAQdxGrab(session, frame, 1);
		CameraServer.getInstance().setImage(frame);
	}

	// If the camera is in use or not
	public boolean getActive() {
		return (session == -1) ? false : true;
	}

	public String getCameraName() {
		if (getActive()) {
			return camName;
		}
		else {
			return "No Camera";
		}
	}
}
