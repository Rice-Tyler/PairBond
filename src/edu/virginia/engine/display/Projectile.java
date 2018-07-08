package edu.virginia.engine.display;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import edu.virginia.engine.events.Event;
import edu.virginia.engine.events.IEventListener;
import edu.virginia.engine.events.ProjectileEvent;


public class Projectile extends Sprite implements IEventListener {
	private int fuse = 100;
	private int countdown = 0;
	private Double spread = 0.0;
	private Explosion exp;
	private String blastType = "Round";
	private String sub = "";
	private String img = "";
	
	private ArrayList<Projectile> submunition = new ArrayList<Projectile>();
	public Projectile(String id,String filename,String blastType,Double radius,int damage,int duration,int height,int width,int fuse, Double spread,String sub) {
		super(id,filename);
		this.img = filename;
		this.blastType = blastType;
		this.setRadius(radius);
		this.fuse=fuse;
		this.spread=spread;
		this.sub = sub;
		this.exp=new Explosion(this.getId(),damage,duration,blastType,radius,height,width);
		this.setScale(.1);
		this.setSolid(false);
		this.setNormalUp(false);
		this.setMass(1.0);
		this.setyMax(100);
		this.setxMax(100);
		for(int x = 0; x<submunition.size();x++) {
			Projectile subm = submunition.get(x);
			subm.setSolid(false);
			this.addChild(subm);
		}
		this.addChild(exp);
	}
	public Double getSpread() {
		return spread;
	}
	public void setSpread(Double spread) {
		this.spread = spread;
	}
	public String getSub() {
		return sub;
	}
	public void setSub(String sub) {
		this.sub = sub;
	}
	public String getImg() {
		return img;
	}
	public void setImg(String img) {
		this.img = img;
	}
	public Explosion getExp() {
		return exp;
	}
	public void setExp(Explosion exp) {
		this.exp = exp;
	}
	public String getBlastType() {
		return blastType;
	}
	public void setBlastType(String blastType) {
		this.blastType = blastType;
	}
	public int getFuse() {
		return fuse;
	}
	public void setFuse(int fuse) {
		this.fuse = fuse;
	}
	public int getCountdown() {
		return countdown;
	}
	public void incCountdown() {
		this.countdown++;
	}
	public ArrayList<Projectile> getSubmunition() {
		return submunition;
	}
	public void setSubmunition(ArrayList<Projectile> submunition) {
		this.submunition = submunition;
	}
	public void addSubmunition(Projectile p) {
		p.setFuse(p.fuse+this.fuse);
		this.submunition.add(p);
		this.addChild(p);
	}
	@Override
	public void handleEvent(Event event) {
		if(event.getEventType() == ProjectileEvent.PROJECTILE_EXPLODE) {
			ProjectileEvent e = (ProjectileEvent)event;
			if(e.getId() == this.getId()) {
				this.setSolid(false);
				this.setVisible(false);
			}
		}
	}
	
	public static Projectile loadProjectile(String id, String filename) {
		
		String line = null;
		String blastType = null;
		Double r = 0.0;
		Integer damage = 0;
		Integer duration = 1;
		Integer height = 0;
		Integer width = 0;
		Integer fuse = 10;
		Double spread = 0.0;
		String sub = "none";
		Integer sub_num = 0;
		String img = "proj.png";
		try {
			String f = String.format("weapons/%s", filename);
			File file = new File(f);
	//		File[] filesList = dir.listFiles();
			
			FileReader fr = new FileReader(file);
			BufferedReader br = new BufferedReader(fr);
			
			line = br.readLine();
			img = line;
			System.out.println(blastType);	
//			System.out.println(blastType.equals("Round"));
			
			/* blast type */
			line = br.readLine();
			blastType = line;
			blastType = blastType.substring(0, 5);
			System.out.println(blastType);	
			System.out.println(blastType.equals("Round"));
			
			/*blastRadius*/
			line = br.readLine();
			r = Double.valueOf(line);
			System.out.println(r);
			
			line = br.readLine();
			damage = Integer.valueOf(line);
			System.out.println(damage);
			
			line = br.readLine();
			duration = Integer.valueOf(line);
			System.out.println(duration);
			
			line = br.readLine();
			height = Integer.valueOf(line);
			System.out.println(height);
			
			line = br.readLine();
			width = Integer.valueOf(line);
			System.out.println(width);
			
			line = br.readLine();
			fuse = Integer.valueOf(line);
			System.out.println(fuse);
			
			line = br.readLine();
			spread = Double.valueOf(line);
			System.out.println(spread);
			
			line = br.readLine();
			sub = line;
			System.out.println(sub);
			
			line = br.readLine();
			sub_num = Integer.valueOf(line);
			System.out.println(sub_num);
			
            br.close(); 
			
		}
		catch(FileNotFoundException ex) {
            System.out.println(
                "Unable to open file '" + 
                filename + "'");                
        }
        catch(IOException ex) {
            System.out.println(
                "Error reading file '" 
                + filename + "'");                  
        }
		Projectile p = new Projectile(id, img, blastType, r, damage, duration, height, width, fuse, spread,sub);
//		
		if(!sub.equals("none")) {
			for(int x =0;x<sub_num;x++) {
				String path = String.format("weapons/%s",sub);
				System.out.println(path);
				Projectile sp = loadProjectile(String.format("s%d", x),path);
				p.addSubmunition(sp);
			}
		}
		return p;
	}
	public static void saveProjectile(Projectile p, String filename) {
		try {
			filename = String.format("weapons/%s", filename);
			File f = new File(filename);
			FileWriter fw = new FileWriter(f);
			BufferedWriter bw = new BufferedWriter(fw);
			Explosion e = p.getExp();
			
			String P = String.format(
					"%s\n" + "%s\n" + "%f\n" +
					"%d\n" + "%d\n" + "%d\n" +
					"%d\n" + "%d\n" + "%f\n" +
					"%s\n" + "%d\n",
					p.getImg(),p.getBlastType(), p.getRadius(),
					e.getDamage(), e.getDuration(),  e.getHeight(),
					e.getWidth(),p.getFuse(),p.getSpread(),
					p.getSub(),p.getSubmunition().size());
			bw.write(P);
			bw.close();
		}catch(IOException ex) {
            System.out.println(
                    "Error writing to file '"
                    + filename + "'");
            }
	}
	public static void main(String[] args){
		Projectile K = Projectile.loadProjectile("id", "standard");
		Projectile.saveProjectile(K, "test");
		Projectile J = Projectile.loadProjectile("id", "test");
	}
	
}
