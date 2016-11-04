package com.theironyard;

import javafx.collections.transformation.SortedList;

import java.util.HashMap;
import java.util.HashSet;
import java.util.stream.Collectors;

public class Main {

    public static HashSet<Card> createDeck() {
        HashSet<Card> deck = new HashSet<>();
        for (Card.Suit suit : Card.Suit.values()) {
            for (Card.Rank rank : Card.Rank.values()) {
                Card c = new Card(suit, rank);
                deck.add(c);
            }
        }
        return deck;
    }


    public static HashSet<HashSet<Card>> createHands(HashSet<Card> deck) {
        HashSet<HashSet<Card>> hands = new HashSet<>();
        for (Card c1 : deck) {
            HashSet<Card> deck2 = (HashSet<Card>) deck.clone();
            deck2.remove(c1);
            for (Card c2 : deck2) {
                HashSet<Card> deck3 = (HashSet<Card>) deck2.clone();
                deck3.remove(c2);
                for (Card c3 : deck3) {
                    HashSet<Card> deck4 = (HashSet<Card>) deck3.clone();
                    deck4.remove(c3);
                    for (Card c4 : deck4) {
                        HashSet<Card> hand = new HashSet<>();
                        hand.add(c1);
                        hand.add(c2);
                        hand.add(c3);
                        hand.add(c4);
                        hands.add(hand);
                    }
                }
            }
        }
        return hands;
    }

    public static boolean isFlush(HashSet<Card> hand) {
        HashSet<Card.Suit> suits = new HashSet<>();
        for (Card c : hand) {
            suits.add(c.suit);
        }
        return suits.size() == 1;
    }

    public static boolean fourOfAKind (HashSet<Card> hand) {
        HashSet<Card.Rank> ranks = new HashSet<>();
        for (Card c : hand) {
            ranks.add(c.rank);
        }
        return ranks.size() == 1;
    }

    public static boolean threeOfAKind (HashSet<Card> hand) {
        HashMap<Card.Rank, Integer> ranks = new HashMap<>();
        for (Card c : hand) {
            if (!ranks.containsKey(c.rank)) {
                ranks.put(c.rank, 1);
            }
            else {
                int tally = ranks.get(c.rank);
                int newTally = tally + 1;
                ranks.put(c.rank, newTally);
            }
        }
        return ranks.containsValue(3);
    }

    public static boolean isPair (HashSet<Card> hand) {
        HashMap<Card.Rank, Integer> ranks = new HashMap<>();
        for (Card c : hand) {
            if (!ranks.containsKey(c.rank)) {
                ranks.put(c.rank, 1);
            }
            else {
                int tally = ranks.get(c.rank);
                int newTally = tally + 1;
                ranks.put(c.rank, newTally);
            }
        }
        return ranks.containsValue(2);
    }



    public static void main(String[] args) {
        HashSet<Card> deck = createDeck();
        HashSet<HashSet<Card>> hands = createHands(deck);
        hands = hands.stream()
                .filter(Main::isPair)
                .collect(Collectors.toCollection(HashSet::new));
        System.out.println(hands.size());

    }
}
