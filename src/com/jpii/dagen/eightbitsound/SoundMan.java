package com.jpii.dagen.eightbitsound;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.ByteBuffer;

import javax.sound.sampled.AudioFileFormat;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioFormat.Encoding;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;

//import javax.sound.midi.MidiChannel;
//import javax.sound.sampled.*;

public class SoundMan {
	public static void playSound(StepList sound,double duration) {
		float rate = 44100f;
		AudioFormat format = new AudioFormat(Encoding.PCM_SIGNED,rate,Short.SIZE,1,(Short.SIZE / 8) * 1,rate,true);
		ByteBuffer data = ByteBuffer.allocate((((int) Math.ceil(rate * duration)) * 1) * (Short.SIZE / 8));
		for (int steps = 0; steps < (int) Math.ceil(rate * duration) && steps < sound.size(); steps++) {
			data.putShort((short)(sound.getStep(steps) * 100000));
		}
		AudioInputStream stream = new AudioInputStream(new ByteArrayInputStream(data.array()), format, ((int) Math.ceil(rate * duration))*2);
		Clip cp = null;
		try {
			cp = AudioSystem.getClip();
			cp.open(stream);
		} catch (LineUnavailableException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		cp.start();
		cp.drain();
	}
	
	public static boolean exportSoundFile(StepList sound, double duration, SoundFileFormat fformat, String filename) {
		float rate = 44100f;
		AudioFormat format = new AudioFormat(Encoding.PCM_SIGNED,rate,Short.SIZE,1,(Short.SIZE / 8) * 1,rate,true);
		ByteBuffer data = ByteBuffer.allocate((((int) Math.ceil(rate * duration)) * 1) * (Short.SIZE / 8));
		for (int steps = 0; steps < (int) Math.ceil(rate * duration) && steps < sound.size(); steps++) {
			data.putShort((short)(sound.getStep(steps) * 100000));
		}
		AudioInputStream stream = new AudioInputStream(new ByteArrayInputStream(data.array()), format, ((int) Math.ceil(rate * duration))*2);
		File outputAudio = new File(filename);
		try
		{
			outputAudio.createNewFile();
		}
		catch (Throwable thrw) {
			
		}
		try {
			if (fformat == SoundFileFormat.AIFC) {
				if (!AudioSystem.isFileTypeSupported(AudioFileFormat.Type.AIFC)) {
					return false;
				}
				AudioSystem.write(stream, AudioFileFormat.Type.AIFC, outputAudio);
			}
			else if (fformat == SoundFileFormat.AIFF) {
				if (!AudioSystem.isFileTypeSupported(AudioFileFormat.Type.AIFF)) {
					return false;
				}
				AudioSystem.write(stream, AudioFileFormat.Type.AIFF, outputAudio);
			}
			else if (fformat == SoundFileFormat.AU) {
				if (!AudioSystem.isFileTypeSupported(AudioFileFormat.Type.AU)) {
					return false;
				}
				AudioSystem.write(stream, AudioFileFormat.Type.AU, outputAudio);
			}
			else if (fformat == SoundFileFormat.B8S) {
				return SoundMan.saveStepSound(sound, filename);
			}
			else if (fformat == SoundFileFormat.SND) {
				if (!AudioSystem.isFileTypeSupported(AudioFileFormat.Type.SND)) {
					return false;
				}
				AudioSystem.write(stream, AudioFileFormat.Type.SND, outputAudio);
			}
			else if (fformat == SoundFileFormat.WAV) {
				if (!AudioSystem.isFileTypeSupported(AudioFileFormat.Type.WAVE)) {
					return false;
				}
				AudioSystem.write(stream, AudioFileFormat.Type.WAVE, outputAudio);
			}
			else {
				return false;
			}
			return true;
		} catch (Throwable e) {
			e.printStackTrace();
		}
		
		return false;
	}
	
	public static boolean saveStepSound(StepList steps, String filename) {
		FileOutputStream fos = null;
		ObjectOutputStream out = null;
		try {
			fos = new FileOutputStream(filename);
			out = new ObjectOutputStream(fos);
			out.writeObject(steps);
			out.close();
			return true;
		} catch (IOException ex) {
			ex.printStackTrace();
		}
		return false;
	}
	
	public static StepList loadStepSound(String filename) {
		FileInputStream fis = null;
		ObjectInputStream in = null;
		StepList steps = null;
		try {
			fis = new FileInputStream(filename);
			in = new ObjectInputStream(fis);
			steps = (StepList)in.readObject();
			in.close();
		} catch (IOException ex) {
			ex.printStackTrace();
		} catch (ClassNotFoundException ex) {
			ex.printStackTrace();
		}
		return steps;
	}
}
