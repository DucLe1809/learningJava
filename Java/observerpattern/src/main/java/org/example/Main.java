package org.example;
/* Des: A camera system is observing an experimental subject and notify changes in action of the
subject to the observer
 */
//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        CameraSystem cameraSystem = new CameraSystem();
        Announcer speaker =  new Announcer();

        cameraSystem.addObserver(speaker);
        cameraSystem.setAction("jump");
        cameraSystem.setAction("play");
    }
}