/** package Strukture der Klasse InputOutput **/

package de.hfu;

/** Ein Java Programm, bestehend aus einer Quelltextdatei, mit einer main-Methode,
welches eine Zeichenkette von der Tastatur einliest, in Großbuchstaben umwandelt und wieder
in die Konsole ausgibt. **/

public class InputOutput {
	 @SuppressWarnings("resource")
	public static void main( String[] args ) {
		    String input = new java.util.Scanner( System.in ).nextLine();

		    for ( int i = 0; i < input.length(); i++ ) {
		      char c = input.charAt( i );
		      if ( Character.isWhitespace( c ) )
		        System.out.print( '_' );
		      else if ( Character.isLetter( c ) )
		        System.out.print( Character.toUpperCase( c ) );
		    }
	  }
}