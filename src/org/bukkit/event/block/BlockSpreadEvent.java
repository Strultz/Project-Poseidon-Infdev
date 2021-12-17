package org.bukkit.event.block;

import org.bukkit.block.Block;
import org.bukkit.block.BlockState;
/**
 * Called when a block spreads based on world conditions.
 * Use {@link BlockFormEvent} to catch blocks that "randomly" form instead of actually spread.
 *<p />
 * Examples:
 *<ul>
 *     <li>Fire spreading.</li>
 * </ul>
 *<p />
 * If a Block Spread event is cancelled, the block will not spread.
 * @see BlockFormEvent
 */
public class BlockSpreadEvent extends BlockEvent {
    private Block source;
    protected boolean cancel;

    public BlockSpreadEvent(Block block, Block source, BlockState newState) {
        super(Type.BLOCK_SPREAD, block);
        this.source = source;
    }

    /**
     * Gets the source block involved in this event.
     *
     * @return the Block for the source block involved in this event.
     */
    public Block getSource() {
        return source;
    }
    
    public boolean isCancelled() {
        return cancel;
    }

    public void setCancelled(boolean cancel) {
        this.cancel = cancel;
    }
}
