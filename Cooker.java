package AutoCooker;

import java.awt.EventQueue;

import javax.swing.*;

import java.awt.BorderLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
import com.jgoodies.forms.factories.FormFactory;

import java.awt.EventQueue;
import java.awt.Graphics;
import java.text.AttributedCharacterIterator;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

import org.powerbot.core.event.listeners.MessageListener;
import org.powerbot.core.event.events.MessageEvent;
import org.powerbot.core.event.listeners.PaintListener;
import org.powerbot.core.script.ActiveScript;
import org.powerbot.core.script.job.state.Node;
import org.powerbot.core.script.job.state.Tree;
import org.powerbot.game.api.Manifest;
import org.powerbot.game.api.methods.tab.Skills;
import org.powerbot.game.api.util.Random;

import AutoCooker.Jobs.Banking;
import AutoCooker.Jobs.Cook;
import AutoCooker.Utils.Food;
import AutoCooker.Utils.Paint;

@Manifest(authors = ("Jdsfighter"), name = "JDCooker", description = "AIO Cooking Script", version = 0.1)
public class Cooker extends ActiveScript implements PaintListener, MessageListener {
	public static Food cfood;
	public static String sFood = "Nothing", sStatus = "Waiting",sTTL="00 hrs";
	public static long lCooked = 0, lBurned =0, lCookPh =0,
			lLevel=0, lXPHr=0,lTime=1, lILevel=0, lIXP=0, lXPLevel=0;

	private final List<Node> jobsCollection = Collections
			.synchronizedList(new ArrayList<Node>());
	private Tree jobContainer = null;

	public final void provide(final Node... jobs) {
		for (final Node job : jobs) {
			jobsCollection.add(job);
		}
		jobContainer = new Tree(jobsCollection.toArray(new Node[jobsCollection
				.size()]));
	}

	@Override
	public void onStart() {
		sStatus = "Waiting for user...";
		lILevel=Skills.getLevel(Skills.COOKING);
		lLevel=Skills.getLevel(Skills.COOKING);
		lTime=System.currentTimeMillis();
		lIXP=Skills.getExperience(Skills.COOKING);
		lXPLevel=Skills.getExperienceToLevel(Skills.COOKING, (int) (lLevel+1));
		GUI gui = new GUI();
		gui.setVisible(true);

	}
	
	@Override
	public void messageReceived(MessageEvent arg0) {
		if (arg0.getMessage().contains("You successfully cook")) {
			lCooked++;
		} else if (arg0.getMessage().contains("You accidentally")) {
			lBurned++;
		}
		
	}
	
	public static String getTime() {
		DateFormat outFormat = new SimpleDateFormat("HH:mm:ss");
		DateFormat outFormat2 = new SimpleDateFormat("HH");
		outFormat.setTimeZone(TimeZone.getTimeZone("UTC"));

		Date d = new Date(System.currentTimeMillis()-lTime);
		String result = outFormat.format(d);
		
		lXPLevel=Skills.getExperienceToLevel(Skills.COOKING, (int) (lLevel+1));
		lLevel=Skills.getLevel(Skills.COOKING);
		lXPHr=((Skills.getExperience(Skills.COOKING)-lIXP)*3600000)/(System.currentTimeMillis()-lTime);
		lCookPh=(lCooked*3600000)/(System.currentTimeMillis()-lTime);
		if (lXPHr>0) {
			Date xp = new Date(Math.round(((double)lXPLevel/(double)lXPHr)*3600000));
			sTTL=String.valueOf(outFormat.format(xp));
		}
		
		return result;
	}
	

	public void Ready() {
		sFood = cfood.name();
		provide(new Cook(), new Banking());
	}

	@Override
	public void onRepaint(Graphics arg0) {
		Paint.drawMouse(arg0);
		Paint.drawPaint(arg0);

	}

	@Override
	public int loop() {
		if (jobContainer != null) {
			final Node job = jobContainer.state();
			if (job != null) {
				jobContainer.set(job);
				getContainer().submit(job);
				job.join();
			}
		}
		return Random.nextInt(10, 50);
	}

	public class GUI extends JFrame {
		public GUI() {
			init();

		}

		private void init() {
			setType(Type.UTILITY);
			setTitle("JDCooker Settings");
			setBounds(100, 100, 211, 139);
			getContentPane().setLayout(null);

			String[] cFood = { "Shrimp", "Manta", "Herring", "Trout", "Cod",
					"Pike", "Salmon", "Tuna", "Lobster", "Crayfish",
					"Karambwajin", "Sardine", "Anchovies", "Macherel",
					"Karambwan", "RainbowFish", "CaveEel", "Bass", "Swordfish",
					"LavaEel", "Monkfish", "Shark", "SeaTurtle" };
			final JComboBox cboFood = new JComboBox(cFood);
			cboFood.setBounds(10, 40, 175, 20);
			getContentPane().add(cboFood);

			JLabel lblType = new JLabel("Type of Food:");
			lblType.setBounds(10, 11, 72, 14);
			getContentPane().add(lblType);

			JButton btnStart = new JButton("Start");
			btnStart.setBounds(89, 71, 96, 23);
			btnStart.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					cfood = Food.valueOf((String) cboFood.getSelectedItem());
					Ready();
					setVisible(false);
				}
			});
			getContentPane().add(btnStart);
		}
	}



}
