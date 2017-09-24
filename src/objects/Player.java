package objects;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.LinkedList;

import framework.GameObject;
import framework.ObjectId;
import framework.SpriteLoader;
import window.Animation;
import window.Game;
import window.Handler;

public class Player extends DamageableObject {

	private float width = 44, height = 114;
	private float xOffset = 25;

	private int direction = 1;// 0 = left, 1 = right
	private float gravity = 0.3f;
	private final float MAX_SPEED = 10;
	private boolean flip = false;

	private Handler handler;
	private SpriteLoader sprites = Game.getSprites();

	private Animation ANIM_PLAYER_WALK;
	private Animation ANIM_PLAYER_STAND;
	private Animation ANIM_PLAYER_FLIP;

	public Player(float x, float y, Handler handler, ObjectId id) {
		super(x, y, "Mitch", id);
		this.handler = handler;

		/*ANIMATIONS*/
		ANIM_PLAYER_WALK = new Animation(3, sprites.PLAYER_WALK);
		ANIM_PLAYER_STAND = new Animation(10, sprites.PLAYER_STAND);
		ANIM_PLAYER_FLIP = new Animation(5, sprites.PLAYER_FLIP);
	}

	@Override
	public void tick(LinkedList<GameObject> object) {
		x += velX;
		y += velY;

		if (falling || jumping) {
			velY += gravity;

			if (velY > MAX_SPEED)
				velY = MAX_SPEED;
		}
		collision(object);
		ANIM_PLAYER_STAND.runAnimation();
		ANIM_PLAYER_WALK.runAnimation();

		// flipping double jump
		if (ANIM_PLAYER_FLIP.getCurrentFrame() == 5) {
			flip = false;
			ANIM_PLAYER_FLIP.setCurrentFrame(0);
		}
			if (flip == true)
			ANIM_PLAYER_FLIP.runAnimation();
		//////////////////////
	}

	private void collision(LinkedList<GameObject> object) {
		for (int i = 0; i < object.size(); i++) {
			GameObject tempObject = handler.object.get(i);

			if (tempObject.getId() == ObjectId.BLOCK) {

				if (getBoundsTop().intersects(tempObject.getBounds())) {
					y = tempObject.getY() + 35;
					velY = 0;
				}

				if (getBoundsRight().intersects(tempObject.getBounds())
						|| tempObject.getBounds().intersects(getBoundsRight())) {
					x = (float) (tempObject.getX() - width - xOffset);
				}

				if (getBoundsLeft().intersects(tempObject.getBounds())
						|| tempObject.getBounds().intersects(getBoundsLeft())) {
					x = (float) (tempObject.getX() + tempObject.getBounds().getWidth() - xOffset);
				}
			}
		}
		for (int i = 0; i < object.size(); i++) {
			GameObject tempObject = handler.object.get(i);
			if (tempObject.getId() == ObjectId.BLOCK) {
				if ((getBounds().intersects(tempObject.getBounds()))) {
					y = tempObject.getY() - height + 1;
					velY = 0;
					falling = false;
					jumping = false;
					return;
				} else {
					falling = true;
				}
			}
		}
	}

	@Override
	public void render(Graphics g) {

		renderName(g, x+35, y-20);
		renderHealthBar(g, x+25, y-16, (int) 50, 8);
		
		/////////////////ANIMATIONS/////////////////////////////////////////
		if (flip) {
			if (direction == 1)
				ANIM_PLAYER_FLIP.drawAnimation(g, (int) x, (int) y);
			if (direction == 0)
				ANIM_PLAYER_FLIP.drawFlippedAnimation(g, (int) x, (int) y);
		} else {

			if (velY == 0) {
				if (velX != 0) {
					if (direction == 1) {
						ANIM_PLAYER_WALK.drawAnimation(g, (int) x, (int) y);
					} else if (direction == 0) {
						ANIM_PLAYER_WALK.drawFlippedAnimation(g, (int) x, (int) y);
					}
				} else {
					if (direction == 1) {
						ANIM_PLAYER_STAND.drawAnimation(g, (int) x, (int) y);
					} else if (direction == 0) {
						ANIM_PLAYER_STAND.drawFlippedAnimation(g, (int) x, (int) y);
					}
				}
			} else {
				if (velY > 0) {
					if (direction == 1)
						g.drawImage(sprites.PLAYER_LANDING, (int) x, (int) y, null);
					if (direction == 0)
						g.drawImage(sprites.PLAYER_LANDING, (int) x + sprites.PLAYER_LANDING.getWidth(), (int) y,
								-sprites.PLAYER_LANDING.getWidth(), sprites.PLAYER_LANDING.getHeight(), null);
				}
				if (velY < 0) {
					if (direction == 1)
						g.drawImage(sprites.PLAYER_JUMPING, (int) x, (int) y, null);
					if (direction == 0)
						g.drawImage(sprites.PLAYER_JUMPING, (int) x + sprites.PLAYER_JUMPING.getWidth(), (int) y,
								-sprites.PLAYER_JUMPING.getWidth(), sprites.PLAYER_JUMPING.getHeight(), null);
				}
			}
		}
		////////////////////END OF ANIMATIONS////////////////////////

	}

	public int getDirection() {
		return direction;
	}

	public void setDirection(int dir) {
		this.direction = dir;
	}

	public void setFlip(boolean b) {
		this.flip = b;
	}

	@Override
	public Rectangle getBounds() {
		return new Rectangle((int) ((int) x + xOffset + (width / 2) - ((width / 2) / 2)),
				(int) ((int) y + (height / 2)), (int) width / 2, (int) height / 2);
	}

	public Rectangle getBoundsTop() {
		return new Rectangle((int) ((int) x + xOffset + (width / 2) - ((width / 2) / 2)), (int) y, (int) width / 2,
				(int) height / 2);
	}

	public Rectangle getBoundsRight() {
		return new Rectangle((int) ((int) x + xOffset + (width) - 5), (int) y + 5, 5, (int) height - 15);
	}

	public Rectangle getBoundsLeft() {
		return new Rectangle((int) ((int) x + xOffset), (int) y + 5, 5, (int) height - 15);
	}

}
