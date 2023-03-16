
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

public class BasicEnemy extends GameObject {
	
	private Handler handler;
	private Player player;
	private char[][] bfsGraph;
	private char[][] tempGraph = new char[784][784];
	private long lastAttackTimer, attackCooldown = 2000, attackTimer = attackCooldown;
	private long lastTimer, cooldown = 300, timer = cooldown;
	private long lastTimer2, cooldown2 = 60, timer2 = cooldown2;
	private List<Node> tempList;
	
	public BasicEnemy(int x, int y, ID id, Handler handler) {
		super(x, y, id);
		this.handler = handler;
		this.bfsGraph = handler.getBfsGraph();
		this.health = 50;
		
		for (int i = 0; i < handler.object.size(); i++) {
			if (handler.object.get(i).getId() == ID.Player) {
				player = (Player) handler.object.get(i);
				break;
			}
		}
		
		handler.getHud().setEnemiesLeft(handler.getHud().getEnemiesLeft() + 1);
	}
	
	public List<Node> shortestPath() {
		for (int i = 0; i < bfsGraph.length; i++) {
			for (int j = 0; j < bfsGraph[i].length; j++) {
				tempGraph[j][i] = bfsGraph[j][i];
			}
		}
		Map<Node, Node> parents = new HashMap<Node, Node>();
		Node start = null;
		Node end = null;
		tempGraph[y/2][x/2] = 'S';
		for (int i = 0; i < 16; i ++) {
			for (int j = 0; j < 16; j++) {
				tempGraph[player.getY()/2 + j][player.getX()/2 + i] = 'D';
			}
		}
		//tempGraph[player.getY()][player.getX()] = 'D';
		// find the start node
		start = new Node(x/2, y/2, tempGraph[y/2][x/2]);
		// traverse every node using breadth first search until reaching the destination

		Queue<Node> temp = new LinkedList<Node>();
		temp.add(start);
		parents.put(start, null);

		boolean reachDestination = false;
		while (!temp.isEmpty() && !reachDestination) {
			Node currentNode = temp.remove();
			List<Node> children = getChildren(currentNode);
			for (Node child : children) {
				// Node can only be visited once
				if (!parents.containsKey(child)) {
					parents.put(child, currentNode);
					char value = child.getValue();
					if (value == '1') {
						temp.add(child);
					} else if (value == 'D') {
						temp.add(child);
						reachDestination = true;
						end = child;
						break;
					}
				}
			}
		}
		if (end == null) {
			throw new RuntimeException("can't find end node");
		}
		// get the shortest path
		Node node = end;
		List<Node> path = new ArrayList<Node>();
		while (node != null) {
			path.add(0, node);
			node = parents.get(node);
		}
		// do movement code here
		return path;
	}
	
	private List<Node> getChildren(Node parent) {
		List<Node> children = new ArrayList<Node>();
		int x = parent.getX();
		int y = parent.getY();
		int inc = 4;
		if (x - inc >= 0) {
			Node child = new Node(x - inc, y, tempGraph[y][x-inc]);
			children.add(child);
		}
		if (y - inc >= 0) {
			Node child = new Node(x, y - inc, tempGraph[y-inc][x]);
			children.add(child);
		}
		if (x + inc < tempGraph[0].length) {
			Node child = new Node(x + inc, y, tempGraph[y][x+inc]);
			children.add(child);
		}
		if (y + inc < tempGraph.length) {
			Node child = new Node(x, y + inc, tempGraph[y+inc][x]);
			children.add(child);
		}
		return children;
	}
	
	public void attack(GameObject tempObject) {
		// this gets the time paused and then subtracts it so that
		// when in pause state the game time also pauses
		long pausedTime = handler.getEndPause() - handler.getStartPause();
		if (!handler.isYet()) {
			attackTimer -= pausedTime;
			handler.setYet(true);
		}
		attackTimer += System.currentTimeMillis() - lastAttackTimer;
		lastAttackTimer = System.currentTimeMillis();
		if (attackTimer < attackCooldown)
			return;
		
		if (handler.isShielded())
			handler.setShielded(false);
		else {
			tempObject.health--;
			handler.setHurt(true);
		}
		attackTimer = 0;
	}
	
	private void cooldown() {
		timer += System.currentTimeMillis() - lastTimer;
		lastTimer = System.currentTimeMillis();
		if (timer < cooldown)
			return;
		
		tempList = shortestPath();
    	
		timer = 0;
	}
	private void enemyMove() {
		timer2 += System.currentTimeMillis() - lastTimer2;
		lastTimer2 = System.currentTimeMillis();
		if (timer2 < cooldown2)
			return;
		
		if (tempList.size() > 1) {
    		tempList.remove(0);
		}
		x = tempList.get(0).getX()*2;
		y = tempList.get(0).getY()*2;

    	timer2 = 0;
	}
	
	public void tick() {
		collision();
		
		cooldown();
		enemyMove();
		
		if (this.health <= 0) {
			handler.removeObject(this);
			handler.getHud().setEnemiesLeft(handler.getHud().getEnemiesLeft() - 1);
			handler.getHud().setGold(handler.getHud().getGold() + 200);
		}
	}
	
	public boolean placeFree(Rectangle myRect, Rectangle otherRect) {
		if (myRect.intersects(otherRect))
			return false;
		return true;
	}
	
	public void collision() {
		for (int i = 0; i < handler.object.size(); i++) {
			GameObject tempObject = handler.object.get(i);
			//Enemy Collision With Player, Enemy Attacks With A 2 Second Cooldown
			if (tempObject.getId() == ID.Player) {
				if (!placeFree(getBounds(), tempObject.getBounds())) {
					attack(tempObject);
				}
			}
		}
	}

	public void render(Graphics g) {
		g.drawImage(Gfx.basicEnemy, x, y, null);
		g.setColor(Color.white);
		g.setFont(new Font("arial", 0, 10));
		g.drawString(this.health + "/50", x + 5, y - 5);
	}

	public Rectangle getBounds() {
		return new Rectangle(x, y, 32, 32);
	}
	
	class Node {
		
		private int x, y;
		private char value;

		public Node(int x, int y, char value) {
			this.x = x;
			this.y = y;
			this.value = value;
		}
		
		public int getX() {
			return x;
		}

		public int getY() {
			return y;
		}

		public char getValue() {
			return value;
		}

		@Override
		public String toString() {
			return "(x: " + x + " y: " + y + ")";
		}
		
		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + x;
			result = prime * result + y;
			return result;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			Node other = (Node) obj;
			if (x != other.x)
				return false;
			if (y != other.y)
				return false;
			return true;
		}

	}

}
