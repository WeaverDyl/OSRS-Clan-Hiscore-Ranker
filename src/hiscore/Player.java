package hiscore;

import java.text.NumberFormat;

/**
 * A class to represent a player. A player has a username, an experience, and a
 * level
 * 
 * @author Dylan Weaver
 *
 */
public class Player implements Comparable<Player> {
	String name; // Represents a players username
	int rank; // Represents a players rank in the official hiscores
	int level; // Represents a players level
	long experience; // Represents a players experience
	
	private int i; // Used as a return for the compareTo method

	/**
	 * A constructor representing a player
	 * 
	 * @param name The username of the player
	 * @param rank The official rank of the player
	 * @param level The level of the player
	 * @param experience The experience of the player
	 */
	public Player(String name, int rank, int level, long experience) {
		this.name = name;
		this.rank = rank;
		this.level = level;
		this.experience = experience;
	}

	/**
	 * Compares the level and experience parameters of two players
	 * 
	 * @param other The other player that is being compared
	 * @return i "1" if the current player's level is greater than the other
	 *         	  player's OR both players have equal levels and the current player's
	 *            experience is greater than the other player's OR both players have
	 *            equal levels and experience, but the current players rank is lower
	 * 
	 *         	  "-1" if the other player's level is greater than the current player's
	 *            OR both players have equal levels and the other player's experience
	 *            is greater than the current player's OR both players have
	 *            equal levels and experience, but the current players rank is higher
	 * 
	 *            "0" never. The returned int will ultimately resort to comparing official
	 *            ranks, and these are never the same regardless of other data.
	 */
	public int compareTo(Player other) {
		if (this.level > other.level) {
			i = 1;
		} else if (other.level > this.level) {
			i = -1;
		} else if (this.level == other.level) {
			if (this.experience == other.experience) {
				// Here, a bigger rank represents a lower placing. eg rank 2 < rank 1.
				return other.rank - this.rank;
			} else {
				i = (int) (this.experience - other.experience);
			}
		}
		return i;
	}

	/**
	 * @return The player's name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @return The player's level
	 */
	public int getLevel() {
		return level;
	}

	/**
	 * @return The player's experience
	 */
	public long getExperience() {
		return experience;
	}

	/**
	 * Represents a player as a string.
	 */
	public String toString() {
		return "\"" + name.replaceAll(" ", "_") + "\"" + " total level: " + level + " with "
				+ NumberFormat.getInstance().format(experience) + " xp";
	}
	
	@Override
	public boolean equals(Object p) {
		boolean result = false;
		
		if (p == this) {
			return true;
		} else if (p instanceof Player) {
			Player player = (Player) p;
			result = name.equals(player.name) && level == player.level && experience == player.experience;
		}
		
		return result;
	}
	
	@Override
	public int hashCode() {
		int result = 17;
		
		result = 31 * (result * name.hashCode());
		result = 31 * (result * rank);
		result = 31 * (result * level);
		result = (int) (31 * (result * experience));
		
		return result;
	}
}
