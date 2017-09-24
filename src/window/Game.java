package window;

import java.awt.Canvas;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;

import abilities.AbilitySelection;
import framework.KeyInput;
import framework.ObjectId;
import framework.SpriteLoader;
import objects.Block;
import objects.Player;

public class Game extends Canvas implements Runnable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7516887150698980623L;
	
	private boolean running = false;
	private Thread thread;
	
	public static int WIDTH, HEIGHT;
	
	private BufferedImage level = null;
	
	//Objects
	AbilitySelection abilitySelection;
	Handler handler;
	Camera cam;
	static SpriteLoader sprites;
	
	private void init() {
		WIDTH = getWidth();
		HEIGHT = getHeight();
				
		BufferedImageLoader loader = new BufferedImageLoader();
		sprites = new SpriteLoader();
		level = loader.loadImage("/level.png");
		
		abilitySelection = new AbilitySelection();
		
		handler = new Handler();
		cam = new Camera(0, 0);
				
		loadImageLevel(level);
		
		this.addKeyListener(new KeyInput(handler));
	}
	
	public synchronized void start() {
		
		if (running)
			return;
		
		running = true;
		thread = new Thread(this);
		thread.start();
		
	}

	@Override
	public void run() {
		
		init();
		this.requestFocus();
		
		/*Game loop*/
		long lastTime = System.nanoTime();
		double amountOfTicks = 64.0;
		double ns = 1000000000 / amountOfTicks;
		double delta = 0;
		long timer = System.currentTimeMillis();
		int updates = 0;
		int frames = 0;
		while(running){
			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;
			while(delta >= 1){
				tick();
				updates++;
				delta--;
			}
			render();
			frames++;
					
			if(System.currentTimeMillis() - timer > 1000){
				timer += 1000;
				System.out.println("FPS: " + frames + " TICKS: " + updates);
				frames = 0;
				updates = 0;
			}
		}
	}
	
	public void tick() {
		handler.tick();
		
		
		for (int i = 0; i < handler.object.size(); i++)
			if (handler.object.get(i).getId() == ObjectId.PLAYER)
				cam.tick(handler.object.get(i));
	}
	
	public void render() {
		
		BufferStrategy bs = this.getBufferStrategy();
		if (bs == null) {
			this.createBufferStrategy(3);
			return;
		}
		
		Graphics g = bs.getDrawGraphics();
		Graphics2D g2d = (Graphics2D) g;
			
		/*Drawing code*/
		g.drawImage(sprites.BG_CLOUDS, 0,0,Game.WIDTH, Game.HEIGHT, null);
		
		g2d.translate(cam.getX(), cam.getY());//beginning of cam
		
		handler.render(g);
		
		g2d.translate(-cam.getX(), -cam.getY());//end of cam
		
		drawGUI(g);
		/**/
		
		g.dispose();
		bs.show();
		
	}
	
	private void loadImageLevel(BufferedImage image) {
		int w = image.getWidth();
		int h = image.getHeight();
		
		for (int xx=0; xx < w; xx++) {
			for (int yy =0; yy<h; yy++) {
				
				int pixel = image.getRGB(xx, yy);
				int red = (pixel >> 16) & 0xff;
				int green = (pixel >> 8) & 0xff;
				int blue = (pixel) & 0xff;
				
				if (red == 255 && green == 255 && blue == 255) { //white pixel, create floor
					handler.addObject(new Block(xx*32,yy*32,ObjectId.BLOCK));
				}
				
				if (red == 0 && green == 0 && blue == 255) { //blue pixel, create player
					handler.addObject(new Player(xx*32, yy*32, handler, ObjectId.PLAYER));
				}
			}
		}
		System.out.println("created "+handler.object.size()+" objects.");
	}
	
	public static SpriteLoader getSprites() {
		return sprites;
	}
	
	private void drawGUI(Graphics g) {
		g.drawImage(sprites.GUI_ABILITYBAR, Game.WIDTH/2-(sprites.GUI_ABILITYBAR.getWidth()/2), Game.HEIGHT-sprites.GUI_ABILITYBAR.getHeight()
				,null);
		drawAbilityIcons(g, abilitySelection);
		
	}

	private void drawAbilityIcons(Graphics g, AbilitySelection abilitySelection) {
		for(int i=0;i<10;i++) {
			BufferedImage icon = abilitySelection.getAbilities()[i].getIcon();
			g.drawImage(icon, Game.WIDTH/2-(sprites.GUI_ABILITYBAR.getWidth()/2)+10+(66*i), Game.HEIGHT-64, null);
		}
		
	}

	public static void main(String args[]) {
		new Window(1280, 720, "PVP Platformer", new Game());
	}
	
}
