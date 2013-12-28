package resourceSystem;

import gameMech.Plane;

/**
 * Created with IntelliJ IDEA.
 * User: ruslan
 * Date: 12/27/13
 * Time: 2:16 AM
 * To change this template use File | Settings | File Templates.
 */
public class PlaneFactory {
    private static final String PATH_TO_LEFT_PLANE_XML = "data/left_plane.xml";
    private static final String PATH_TO_RIGHT_PLANE_XML = "data/right_plane.xml";

    public static Plane getLeftPlane() {
        return (Plane)ObjectFactory.getInstance().getObjectByPath(PATH_TO_LEFT_PLANE_XML);
    }
    public static Plane getRightPlane() {
        return (Plane)ObjectFactory.getInstance().getObjectByPath(PATH_TO_RIGHT_PLANE_XML);
    }
}
