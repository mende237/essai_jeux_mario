package com.mathmaurer.utilitaire;

import java.util.ArrayList;

import com.mathmaurer.audio.Audio;
import com.mathmaurer.jeu.GameManagement;
import com.mathmaurer.objet.Objet;
import com.mathmaurer.objet.Piece;
import com.mathmaurer.personnage.Champignon;
import com.mathmaurer.personnage.Character;
import com.mathmaurer.personnage.Cheban;
import com.mathmaurer.personnage.Mario;
import com.mathmaurer.personnage.Tortue;

public class Contact {
	public enum Position {
		BEHIND, FRONT
	}

	/*
	 * this function manage the collision with each champignon , tortue which are in
	 * an array
	 */
	public static void Champignon(ArrayList<Champignon> champTab, ArrayList<Tortue> tortueTab, Mario mario) {
		for (int i = 0; i < champTab.size(); i++) {
			if (champTab.get(i).isLiving() == true) {// on verifie que le champignon n'est pas mort
				// on verifie si mario tue le champignon ou le contraire
				champTab.get(i).kill(mario);
				// collision du champignon avec la tortue
				for (int j = 0; j < tortueTab.size(); j++) {
					if (tortueTab.get(j).isLiving() == true) {// on verifie que la tortue est vivante
						if (champTab.get(i).near(tortueTab.get(j)) == true) {
							System.out.println("near");
							champTab.get(i).contact(tortueTab.get(j));
						}
					}
				}

				// collision du champignon avec un autre champignon
				for (int j = 0; j < champTab.size(); j++) {
					if (champTab.get(j).isLiving() == true) {
						if (i != j) {
							if (champTab.get(i).near(champTab.get(j)) == true) {
								champTab.get(i).contact(champTab.get(j));
							}
						}
					}
				}
			}

		}

	}

	public static void tortue(ArrayList<Tortue> tortueTab, ArrayList<? extends Character> champTab, Mario mario) {
		for (int i = 0; i < tortueTab.size(); i++) {
			tortueTab.get(i).kill(mario);
			if (tortueTab.get(i).isLiving() == true || tortueTab.get(i).isZombie() == true) {
				// collision de la tortue avec une autre tortue
				for (int j = 0; j < tortueTab.size(); j++) {
					if (i != j && tortueTab.get(j).isLiving() == true) {// on verifie que la tortue n'est pas morte
						if (tortueTab.get(i).near(tortueTab.get(j)) == true) {
							if (tortueTab.get(i).isZombie() == true) {
								tortueTab.get(j).setLiving(false);
							} else {

								tortueTab.get(i).contact(champTab.get(j));
							}
						}
					}
				}

				// collision de la tortue avec un champignon
				for (int j = 0; j < champTab.size(); j++) {
					if (champTab.get(j).isLiving() == true) {
						if (tortueTab.get(i).near(champTab.get(j))) {
							if (tortueTab.get(i).isZombie() == true) {
								champTab.get(j).setLiving(false);
							} else {

								tortueTab.get(i).contact(champTab.get(j));
							}
						}
					}
				}

			}
		}
	}

	public static void piece(ArrayList<Piece> pieceTab, Mario mario) {
		int minus = 0;
		if (pieceTab.size() > 1) {
			int tab[] = around(pieceTab, 0, pieceTab.size() - 1, 0, 30, mario);
			if (tab != null) {
				for (int i = 0; i <= tab[1] - tab[0] - minus; i++) {
					if (mario.contactPiece(pieceTab.get(tab[0] + i)) == true) {
						pieceTab.remove(tab[0] + i);
						minus++;
						mario.setScore(mario.getScore() + 1);
						Audio.playSong("/audio/piece.wav");
					}

				}
			}
		} else if (pieceTab.size() == 1) {
			if (mario.contactPiece(pieceTab.get(0)) == true) {
				pieceTab.remove(0);
				mario.setScore(mario.getScore() + 1);
			}

		}

	}

	/* this function manage the collision with mario and objets */
	public static boolean mario(ArrayList<? extends Objet> objetTab, int objetWidth, Mario mario) {
		int indexMemory = 0, cmptMerge = 0, cmpt = 0;
		boolean enter = false;
		// on determines les objets qui sont autour de mario
		int tab[] = around(objetTab, 0, objetTab.size() - 1, 0, objetWidth, mario);
		if (tab != null) {
			for (int i = 0; i < tab[1] - tab[0] + 1; i++) {
				if (mario.near(objetTab.get(tab[0] + i)) == true) {
					cmptMerge++;
					if (enter == false) {
						indexMemory = tab[0] + i;
						enter = true;
					}

				} else {
					cmpt++;
				}

			}
			// dans le cas ou mario n'est proche d'aucun objet
			if (cmpt == tab[1] - tab[0] + 1) {
				if (mario.isJump() == false) {
					mario.setIsOnObjet(false);
				}
				return false;
			} else {
				//in case of mario is near two objects at the same time 
				//the two objects are merged together
				if (cmptMerge >= 2) {
					mario.contact(objetTab.get(indexMemory), true);
				} else {
					mario.contact(objetTab.get(indexMemory), false);
				}
				
				return true;
			}

		} else {
			if (mario.isJump() == false) {
				mario.setIsOnObjet(false);
			}
			return false;
		}

	}

	public static void tortue(ArrayList<Tortue> tortueTab, ArrayList<Champignon> champTab,
			ArrayList<? extends Objet> objetTab) {
		Cheban tabC[];
		int tabO[];
		if (GameManagement.DOWN() >= 0) {
			for (int j = 0; j < tortueTab.size(); j++) {
				tabO = aroundObjet(objetTab, 0, objetTab.size() - 1, 0, tortueTab.get(j));
				tortueTab.get(j).setBehindObjet(tabO[0]);
				tortueTab.get(j).setFrontObjet(tabO[1]);

				if (champTab.size() >= 1) {
					tabC = aroundCharacter(champTab, 0, champTab.size() - 1, 0, tortueTab.get(j));
					tortueTab.get(j).setBehindCharacter(tabC[0]);
					tortueTab.get(j).setFrontCharacter(tabC[1]);
				}
				/*
				 * on retire le personnage du tableau une meme personne est toujours proche de
				 * lui meme
				 */
				Tortue temp = tortueTab.remove(j);
				if (tortueTab.size() >= 1) {
					tabC = aroundCharacter(tortueTab, 0, tortueTab.size() - 1, 0, temp);
					setNear(temp, tabC[0], Position.BEHIND);
					setNear(temp, tabC[1], Position.FRONT);
				}
				tortueTab.add(j, temp);
			}
			GameManagement.UP();
		}
	}

	/* collision de chaque champignon S */
	public static void chamignon(ArrayList<Champignon> champTab, ArrayList<Tortue> tortueTab,
			ArrayList<? extends Objet> objetTab) {
		Cheban tabC[];
		int tabO[];
		if (GameManagement.DOWN() >= 0) {
			for (int j = 0; j < champTab.size(); j++) {
				tabO = aroundObjet(objetTab, 0, objetTab.size() - 1, 0, champTab.get(j));
				champTab.get(j).setBehindObjet(tabO[0]);
				champTab.get(j).setFrontObjet(tabO[1]);
				if (tortueTab.size() >= 1) {
					tabC = aroundCharacter(tortueTab, 0, tortueTab.size() - 1, 0, champTab.get(j));
					champTab.get(j).setBehindCharacter(tabC[0]);
					champTab.get(j).setFrontCharacter(tabC[1]);
				}

				Champignon temp = champTab.remove(j);
				if (champTab.size() >= 1) {
					tabC = aroundCharacter(champTab, 0, champTab.size() - 1, 0, temp);
					setNear(temp, tabC[0], Position.BEHIND);
					setNear(temp, tabC[1], Position.FRONT);
				}
				champTab.add(j, temp);
			}
			GameManagement.UP();
		}

	}

	/*
	 * this fonction modifies the character closest to a character if the one that is passed
	 * through parameter is closer than the one which was there before
	 */
	public static void setNear(Cheban personnageCible, Cheban personnage, Position position) {
		if (position == Position.BEHIND) {
			if (personnageCible.getBehindCharacter() == null)
				personnageCible.setBehindCharacter(personnage);
			else if (personnage != null) {
				if (personnage.getX() + personnage.getWidth() >= personnageCible.getBehindCharacter().getX()
						+ personnageCible.getBehindCharacter().getWidth()) {
					personnageCible.setBehindCharacter(personnage);
				}
			}
		} else {
			if (personnageCible.getFrontCharacter() == null) {
				personnageCible.setFrontCharacter(personnage);
			} else if (personnage != null) {
				if (personnage.getX() <= personnageCible.getFrontCharacter().getX()) {
					personnageCible.setFrontCharacter(personnage);
				}
			}
		}
	}

	/*
	 * this function return the index of the two object which are extremely near of
	 * abscissa which is passed in parameter of the function
	 * to achieve that it uses the principle of dichotomous search
	 */
	public static int[] around(ArrayList<? extends Objet> tab, int begin, int end, int middle, int precision,
			Character personnage) {
		middle = (begin + end) / 2;
		if (personnage.getX() + personnage.getWidth() < tab.get(middle).getX() - precision) {
			end = middle;
			if (begin < end)
				return around(tab, begin, end, middle, precision, personnage);
			else
				return null;
		} else if (personnage.getX() > tab.get(middle).getX() + tab.get(middle).getWidth() + precision) {
			begin = middle;
			if (begin < end - 1)
				return around(tab, begin, end, middle, precision, personnage);
			else if (begin == end - 1) {
				int array[] = new int[2];
				array[0] = begin;
				array[1] = end;
				return array;
			} else
				return null;
		} else {
			int array[] = new int[2];
			if (middle >= 2 && middle <= end - 2) {
				array[0] = middle - 2;
				array[1] = middle + 2;
			} else {
				array[0] = begin;
				array[1] = end;
			}
			return array;
		}
	}
	
	/* this function gives two the objects which enclose an character
	 * to achieve that it uses the principle of dichotomous search*/
	public static int[] aroundObjet(ArrayList<? extends Objet> tab, int begin, int end, int middle,
			Character personnage) {
		middle = (begin + end) / 2;
		if (personnage.getX() + personnage.getWidth() < tab.get(middle).getX()) {
			end = middle;
			if (begin < end) {
				if (middle > 0) {
					if (personnage.getX() >= tab.get(middle - 1).getX() + tab.get(middle - 1).getWidth()) {
						int array[] = new int[2];
						array[0] = tab.get(middle - 1).getX() + tab.get(middle - 1).getWidth();
						array[1] = tab.get(middle).getX();
						return array;
					}
				}

				return aroundObjet(tab, begin, end, middle, personnage);
			} else {
				int array[] = new int[2];
				array[0] = 0;
				array[1] = tab.get(begin).getX();
				return array;
			}
		} else if (personnage.getX() > tab.get(middle).getX() + tab.get(middle).getWidth()) {
			begin = middle;
			if (begin < end - 1) {
				if (personnage.getX() + personnage.getWidth() < tab.get(middle + 1).getX()) {
					int array[] = new int[2];
					array[0] = tab.get(middle).getX() + tab.get(middle).getWidth();
					array[1] = tab.get(middle + 1).getX();
					return array;
				}
				return aroundObjet(tab, begin, end, middle, personnage);
			} else if (begin == end - 1) {
				if (tab.get(begin).getX() + tab.get(begin).getWidth() < personnage.getX()
						&& personnage.getX() + personnage.getWidth() < tab.get(end).getX()) {
					int array[] = new int[2];
					array[0] = tab.get(begin).getX() + tab.get(begin).getWidth();
					array[1] = tab.get(end).getX();
					return array;
				} else {
					int array[] = new int[2];
					array[0] = tab.get(end).getX() + tab.get(end).getWidth();
					array[1] = 5000;
					return array;
				}
			} else {
				int array[] = new int[2];
				array[0] = tab.get(end).getX() + tab.get(end).getWidth();
				array[1] = 5000;
				return array;
			}
		} else {
			int array[] = new int[2];
			array[0] = tab.get(begin).getX() + tab.get(begin).getWidth();
			array[1] = tab.get(end).getX();
			return array;
		}
	}

	public static void updateTab(ArrayList<? extends Cheban> tab) {
		for (int i = 0; i < tab.size(); i++) {
			if (tab.get(i).isRemove() == true)
				tab.remove(i);
		}
	}

	/* this function give two the characters which enclose an character
	 * to achieve that it uses the principle of dichotomous search*/
	public static Cheban[] aroundCharacter(ArrayList<? extends Cheban> tab, int begin, int end, int middle,
			Character personnage) {
		middle = (begin + end) / 2;
		if (personnage.getX() + personnage.getWidth() < tab.get(middle).getX()) {
			end = middle;
			if (begin < end) {
				if (middle > 0) {
					if (personnage.getX() >= tab.get(middle - 1).getX() + tab.get(middle - 1).getWidth()) {
						Cheban array[] = new Cheban[2];
						array[0] = tab.get(middle - 1);
						array[1] = tab.get(middle);
						return array;
					}
				}

				return aroundCharacter(tab, begin, end, middle, personnage);
			} else {
				Cheban array[] = new Cheban[2];
				array[0] = null;
				array[1] = tab.get(begin);
				return array;
			}
		} else if (personnage.getX() > tab.get(middle).getX() + tab.get(middle).getWidth()) {
			begin = middle;
			if (begin < end - 1) {
				if (personnage.getX() + personnage.getWidth() < tab.get(middle + 1).getX()) {
					Cheban array[] = new Cheban[2];
					array[0] = tab.get(middle);
					array[1] = tab.get(middle + 1);
					return array;
				}
				return aroundCharacter(tab, begin, end, middle, personnage);
			} else if (begin == end - 1) {
				if (tab.get(begin).getX() + tab.get(begin).getWidth() < personnage.getX()
						&& personnage.getX() + personnage.getWidth() < tab.get(end).getX()) {
					Cheban array[] = new Cheban[2];
					array[0] = tab.get(begin);
					array[1] = tab.get(end);
					return array;
				} else {
					Cheban array[] = new Cheban[2];
					array[0] = tab.get(end);
					array[1] = null;
					return array;
				}
			} else {
				Cheban array[] = new Cheban[2];
				array[0] = tab.get(end);
				array[1] = null;
				return array;
			}
		} else {
			Cheban array[] = new Cheban[2];
			array[0] = tab.get(begin);
			array[1] = tab.get(end);
			return array;
		}
	}

}
