package org.bukkit.event.block;

import org.bukkit.event.Listener;

/**
 * Handles all events thrown in relation to Blocks
 */
public class BlockListener implements Listener {

    /**
     * Default Constructor
     */
    public BlockListener() {}

    /**
     * Called when a block is damaged by a player.
     * <p />
     * If a Block Damage event is cancelled, the block will not be damaged.
     *
     * @param event Relevant event details
     */
    public void onBlockDamage(BlockDamageEvent event) {}

    /**
     * Called when we try to place a block, to see if we can build it here or not.
     *<p />
     * Note:
     * <ul>
     *    <li>The Block returned by getBlock() is the block we are trying to place on, not the block we are trying to place.</li>
     *    <li>If you want to figure out what is being placed, use {@link BlockCanBuildEvent#getMaterial()} or {@link BlockCanBuildEvent#getMaterialId()} instead.</li>
     * </ul>
     *
     * @param event Relevant event details
     */
    public void onBlockCanBuild(BlockCanBuildEvent event) {}

    /**
     * Represents events with a source block and a destination block, currently only applies to liquid (lava and water).
     *<p />
     * If a Block From To event is cancelled, the block will not move (the liquid will not flow).
     *
     * @param event Relevant event details
     */
    public void onBlockFromTo(BlockFromToEvent event) {}

    /**
     * Called when a block is ignited. If you want to catch when a Player places fire, you need to use {@link BlockPlaceEvent}.
     *<p />
     * If a Block Ignite event is cancelled, the block will not be ignited.
     *
     * @param event Relevant event details
     */
    public void onBlockIgnite(BlockIgniteEvent event) {}

    /**
     * Called when block physics occurs.
     *
     * @param event Relevant event details
     */
    public void onBlockPhysics(BlockPhysicsEvent event) {}

    /**
     * Called when a block is placed by a player.
     *<p />
     * If a Block Place event is cancelled, the block will not be placed.
     *
     * @param event Relevant event details
     */
    public void onBlockPlace(BlockPlaceEvent event) {}

    /**
     * Called when leaves are decaying naturally.
     *<p />
     * If a Leaves Decay event is cancelled, the leaves will not decay.
     *
     * @param event Relevant event details
     */
    public void onLeavesDecay(LeavesDecayEvent event) {}

    /**
     * Called when a block is destroyed as a result of being burnt by fire.
     *<p />
     * If a Block Burn event is cancelled, the block will not be destroyed as a result of being burnt by fire.
     *
     * @param event Relevant event details
     */
    public void onBlockBurn(BlockBurnEvent event) {}

    /**
     * Called when a block is broken by a player.
     *<p />
     * Note:
     * Plugins wanting to simulate a traditional block drop should set the block to air and utilise their own methods for determining
     *   what the default drop for the block being broken is and what to do about it, if anything.
     *<p />
     * If a Block Break event is cancelled, the block will not break.
     *
     * @param event Relevant event details
     */
    public void onBlockBreak(BlockBreakEvent event) {}

    /**
     * Called when a block spreads based on world conditions.
     * Use {@link BlockFormEvent} to catch blocks that "randomly" form instead of actually spread.
     *<p />
     * Examples:
     *<ul>
     *     <li>Mushrooms spreading.</li>
     *     <li>Fire spreading.</li>
     * </ul>
     *<p />
     * If a Block Spread event is cancelled, the block will not spread.
     *
     * @param event Relevant event details
     */
    public void onBlockSpread(BlockSpreadEvent event) {}
}
