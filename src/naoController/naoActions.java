package naoController;
import java.util.Random;

import com.aldebaran.demo.DetectEmotion;
import com.aldebaran.demo.ExMotion;
import com.aldebaran.qi.Application;
import com.aldebaran.qi.CallError;
import com.aldebaran.qi.helper.EventCallback;
import com.aldebaran.qi.helper.proxies.ALAudioPlayer;
import com.aldebaran.qi.helper.proxies.ALBasicAwareness;
import com.aldebaran.qi.helper.proxies.ALFaceCharacteristics;
import com.aldebaran.qi.helper.proxies.ALLeds;
import com.aldebaran.qi.helper.proxies.ALMemory;
import com.aldebaran.qi.helper.proxies.ALMotion;
import com.aldebaran.qi.helper.proxies.ALSpeechRecognition;
import com.aldebaran.qi.helper.proxies.ALTextToSpeech;
import com.aldebaran.qi.helper.proxies.ALVideoDevice;

public class naoActions {

	public  String[] args;
	public  String robotUrl = "tcp://169.254.195.158:9559";
	public  Application application;
	public static ALTextToSpeech tts;
	public static ALMotion motion;
	public static ALMemory memory;
	public ALLeds leds;
	public static ALVideoDevice video;
	public static ALAudioPlayer audio;
	public static ALSpeechRecognition speechRecognition;
	public static long frontTactilSubscriptionId;
	public static String moduleName;
    private static ALMemory alMemory;
    private static ALBasicAwareness awareness;
    private static ALFaceCharacteristics faceCharac;
    private static DetectEmotion routine;
    private String[] names={"LeftFaceLeds","RightFaceLeds","FaceLeds"};
    private String[] colors={"white", "red", "green", "blue", "yellow", "magenta", "cyan"};
    private String text="Hello world";

	/**
	 * Camera settings
	 */
	
	public static int topCamera = 0;
	public static int resolution = 2; // 640 x 480
	public static int colorspace = 11; // RGB
	public static int frameRate = 10; // FPS
	public Random r = new Random();
	public naoActions(String robotUrl, String[] args) {
		this.robotUrl = robotUrl;
		this.args = args;
		
	}

	public naoActions(String[] args) {
		this.args = args;
		
	}

	public void startConnection() {
		application = new Application(args, robotUrl);
		application.start();
		System.out.println("Successfully connected to the robot");
	}
	
	
	public void standUp(){
		try {
			motion = new ALMotion(application.session());
			tts = new ALTextToSpeech(application.session());
			tts.say("I'm waking up");
			motion.wakeUp();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
	public void rest(){
		try {
			motion = new ALMotion(application.session());
			tts = new ALTextToSpeech(application.session());
			tts.say("du du du");
			motion.rest();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
	public void changeLedColor(int led,int color,float duration) throws Exception
	{
			try
			{
				String name=names[--led];
				String colorName=colors[--color];
				leds = new ALLeds(application.session());
				leds.fadeRGB(name,colorName,duration);
				leds.fadeRGB("FaceLeds","white",5.0f);
			}
			catch (CallError | InterruptedException e)
			{
				e.printStackTrace();
			}
		
	}
	
	
	public void speechSomething(String text) throws Exception {
		try {
			if((text!=null)&&(!text.isEmpty())){
				this.text=text;
				tts = new ALTextToSpeech(application.session());
				tts.say(this.text);
				System.out.println("Say hello world");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void moveIntial() {
		try {
			motion = new ALMotion(application.session());
			motion.wakeUp();
			motion.moveInit();
			motion.setMoveArmsEnabled(true, true); 
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// move forward
	public void moveForward() {
		System.out.println("Method moveForward() called!");
		try {
			motion = new ALMotion(application.session());
			tts = new ALTextToSpeech(application.session());
			tts.say("Careful! I'm coming to get you!");
			motion.moveTo(0.5f, 0f, 0f);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// move left
	public void moveLeft() {
		System.out.println("Method moveLeft() called!");
		try {
			motion = new ALMotion(application.session());
			tts = new ALTextToSpeech(application.session());
			motion.moveTo(0.5f, -1f, 0f, 2f);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// move right
	public void moveRight() {
		System.out.println("Method moveRight() called!");
		try {
			motion = new ALMotion(application.session());
			tts = new ALTextToSpeech(application.session());
			tts.say("Careful! I'm moving right!");
			motion.moveTo(0.5f, 1f, 0f, 2f);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// move Backward
	public void moveBackward() {
		System.out.println("Method moveBackward() called!");
		try {
			motion = new ALMotion(application.session());
			tts = new ALTextToSpeech(application.session());
			tts.say("Careful! I'm moving Backward!");
			motion.moveTo(-0.5f, 0f, 0f);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void moveStop() {
		System.out.println("Method moveStop() called!");
		try {
			motion = new ALMotion(application.session());
			tts = new ALTextToSpeech(application.session());
			tts.say("Careful! I'm stopping!");
			motion.stopMove();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void lookForward() throws CallError, InterruptedException
	{
		try {
			motion = new ALMotion(application.session());
			tts = new ALTextToSpeech(application.session());
			tts.say("I'm looking forward!");
			motion.setAngles("HeadPitch", 0f, .1f);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	public void lookDown() throws CallError, InterruptedException
	{
		try {
			motion = new ALMotion(application.session());
			tts = new ALTextToSpeech(application.session());
			tts.say("I'm looking down!");
			motion.setAngles("HeadPitch", .45f, .1f);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void lookAround() throws CallError, InterruptedException
	{
		try {
			tts = new ALTextToSpeech(application.session());
			tts.say("I'm looking around!");
			motion = new ALMotion(application.session());
			motion.setAngles("HeadYaw", r.nextFloat() / 2 - .25f, .07f);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		motion.setAngles("HeadYaw", r.nextFloat() / 2 - .25f, .07f);
	}
	
	
	public void ExmotionPlay(){
		ExMotion exmotion= new ExMotion();
		exmotion.exMotionPerform(application);
	}

	public void reactToEvent() throws Exception {

		memory = new ALMemory(application.session());

		tts = new ALTextToSpeech(application.session());
		frontTactilSubscriptionId = 0;

		// Subscribe to FrontTactilTouched event,
		// create an EventCallback expecting a Float.
		frontTactilSubscriptionId = memory.subscribeToEvent("FrontTactilTouched", new EventCallback<Float>() {
			@Override
			public void onEvent(Float arg0) throws InterruptedException, CallError {
				// 1 means the sensor has been pressed
				if (arg0 > 0) {
					tts.say("ouch!");
				}
			}
		});
		// Subscribe to RearTactilTouched event,
		// create an EventCallback expecting a Float.
		memory.subscribeToEvent("RearTactilTouched", new EventCallback<Float>() {
			@Override
			public void onEvent(Float arg0) throws InterruptedException, CallError {
				if (arg0 > 0) {
					if (frontTactilSubscriptionId > 0) {
						tts.say("I'll no longer say ouch");
						// Unsubscribing from FrontTactilTouched event
						memory.unsubscribeToEvent(frontTactilSubscriptionId);
					}
				}
			}
		});
		System.out.println("Subscribed to FrontTactilTouched and RearTactilTouched.");
		// Run your application
		// application.run();
		System.out.println("reactToEvent");
	}
	
	
	
    public  void ExSmileDetector_peroform() {
        try {
            application.start();
            alMemory = new ALMemory(application.session());
            awareness = new ALBasicAwareness(application.session());
            tts = new ALTextToSpeech(application.session());
            motion = new ALMotion(application.session());
            faceCharac = new ALFaceCharacteristics(application.session());

            tts.say("hello");
            motion.wakeUp();
            awareness.setEngagementMode("SemiEngaged");
            awareness.setTrackingMode("Head");
            awareness.setStimulusDetectionEnabled("Sound", true);
            awareness.setStimulusDetectionEnabled("Movement", true);
            awareness.setStimulusDetectionEnabled("People", true);
            awareness.setStimulusDetectionEnabled("Touch", true);

            alMemory.subscribeToEvent("RearTactilTouched",
                    new EventCallback<Float>() {

                        @Override
                        public void onEvent(Float touch)
                                throws InterruptedException, CallError {
                            if (touch == 1.0) {
                                System.out.println("Stopping basic awareness");
                                if (routine.isRunning)
                                    routine.interrupt();
                                awareness.stopAwareness();
                            }
                        }
                    });
            alMemory.subscribeToEvent("FrontTactilTouched",
                    new EventCallback<Float>() {

                        @Override
                        public void onEvent(Float touch)
                                throws InterruptedException, CallError {
                            if (touch == 1.0) {
                                System.out.println("Starting basic awareness");
                                awareness.startAwareness();
                            }
                        }
                    });

            alMemory.subscribeToEvent("ALBasicAwareness/HumanTracked",
                    new EventCallback<Integer>() {

                        int trackedHuman = -1;

                        @Override
                        public void onEvent(Integer humanId)
                                throws InterruptedException, CallError {
                            if (trackedHuman != humanId) {
                                trackedHuman = humanId;
                                System.out.println("humanId " + humanId);
                                if (routine != null && routine.isRunning)
                                    routine.interrupt();
                                if (humanId > 0) {
                                    routine = new DetectEmotion(faceCharac,
                                            alMemory, tts);
                                    routine.start();
                                } else {
                                    tts.say("I don't see anybody.");
                                }
                            }
                        }
                    });

            application.run();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
