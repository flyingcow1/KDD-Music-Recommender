/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package SequentialRecommender;

import java.util.ArrayList;

/**
 *
 * @author sarahejones, sns
 */
public class SequentialKNN {

     /* could precompute  this w/ map reduce: finding k nearest neighbors */
public static void createNeighborhoods(ArrayList<Item> items, ArrayList<User> users) {
/* how often does this need to be updated? do we have to? */

//forall items i  //ith iteration
    for (Item i: items) {

//    forall items j  //split this into N parts
        for (Item j : items) {
            double numerator = 0, denominator_left = 0, denominator_right = 0;

//        forall users user
            for (User user : users) {
                double num = 0, den_l = 0, den_r = 0;
                if (user.rated(i) && user.rated(j)) {
                    num = (user.rating(i) - user.averageRating()) * (user.rating(j) - user.averageRating());

                    den_l = (user.rating(i) - user.averageRating());
                    den_l *= den_l;

                    den_r = (user.rating(j) - user.averageRating());
                    den_r *= den_r;
                }
                numerator += num;
                denominator_left += den_l;
                denominator_right += den_r;
            }


            ItemSimilarity is = new ItemSimilarity(i, j, numerator / Math.sqrt(denominator_left * denominator_right));

            //attempt to add to neighborhood (it will only be added if it should be)
            i.addToNeighborhood(is);
        }
        i.print();
    }
}



}