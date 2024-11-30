package vehicleSystem;

import org.opencv.core.*;
import org.opencv.dnn.*;
import org.opencv.highgui.HighGui;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;

import java.util.*;

public class VehicleRecognition {
    public static void main(String[] args) {
        // Load OpenCV library
    	System.load("C:\\Users\\shant\\Downloads\\opencv\\build\\java\\x64\\opencv_java4100.dll");
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);

        // Load YOLO model files
        String modelConfig = "C:\\Users\\shant\\Downloads\\vehicle\\yolov3.cfg";  // Path to YOLO config file
        String modelWeights = "C:\\Users\\shant\\Downloads\\vehicle\\yolov3.weights";  // Path to YOLO weights file
        String classFile = "C:\\Users\\shant\\Downloads\\vehicle\\coco.names";  // Path to class labels (COCO dataset)

        // Load YOLO network
        Net net = Dnn.readNetFromDarknet(modelConfig, modelWeights);
        net.setPreferableBackend(Dnn.DNN_BACKEND_OPENCV);
        net.setPreferableTarget(Dnn.DNN_TARGET_CPU);

        // Load image
        String imagePath = "path/to/your/image.jpg";
        Mat image = Imgcodecs.imread(imagePath);

        // Prepare image for YOLO model (resize and normalization)
        Mat blob = Dnn.blobFromImage(image, 1/255.0, new Size(416, 416), new Scalar(0, 0, 0), true, false);

        // Set the input to the network
        net.setInput(blob);

        // Get output layer names
        List<String> layerNames = net.getLayerNames();
        List<String> outputLayers = new ArrayList<>();
        for (int i = 0; i < layerNames.size(); i++) {
            if (layerNames.get(i).contains("yolo")) {
                outputLayers.add(layerNames.get(i)); // Use layer name, not index
            }
        }

        // Run forward pass to get detections
        List<Mat> detections = new ArrayList<>();
        net.forward(detections, outputLayers);  // Correct method call with List<String>

        // Process detections
        for (Mat detection : detections) {
            for (int i = 0; i < detection.rows(); i++) {
                Mat row = detection.row(i);
                float confidence = (float) row.get(0, 4)[0];  // Cast to float

                // Only consider detections with confidence above a threshold
                if (confidence > 0.5) {
                    // Get the bounding box coordinates
                    int centerX = (int) (row.get(0, 0)[0] * image.cols());
                    int centerY = (int) (row.get(0, 1)[0] * image.rows());
                    int width = (int) (row.get(0, 2)[0] * image.cols());
                    int height = (int) (row.get(0, 3)[0] * image.rows());

                    // Draw the bounding box
                    Rect box = new Rect(centerX - width / 2, centerY - height / 2, width, height);
                    Imgproc.rectangle(image, box, new Scalar(0, 255, 0), 2);

                    // Optionally, display class label (Vehicle types, etc.)
                    String label = "Vehicle";  // Can use the class name from the 'coco.names' file
                    Imgproc.putText(image, label, new Point(centerX, centerY),
                                    Imgproc.FONT_HERSHEY_SIMPLEX, 1.0, new Scalar(255, 0, 0), 2);
                }
            }
        }

        // Save the output image
        Imgcodecs.imwrite("detected_vehicles.jpg", image);

        // Display the result
        HighGui.imshow("Detected Vehicles", image);
        HighGui.waitKey(0);
    }
}
