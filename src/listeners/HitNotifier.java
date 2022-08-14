package listeners;


/**
 * charge to save all the hit listeners (for notify them that hit occurred).
 */
public interface HitNotifier {
    /**
     * Add hl as a listener to hit events.
     * @param hl hit listener.
     */
    void addHitListener(HitListener hl);
    /**
     * Remove hl from the list of listeners to hit events.
     * @param hl hit listener.
     */
    void removeHitListener(HitListener hl);
}
