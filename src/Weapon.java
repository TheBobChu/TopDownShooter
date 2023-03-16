
public abstract class Weapon {

	protected int damage, fireRate, attackTime, ammo;
	
	public Weapon(int damage, int fireRate, int attackTime, int ammo) {
		this.damage = damage;
		this.fireRate = fireRate;
		this.attackTime = attackTime;
		this.ammo = ammo;
	}
	
}
