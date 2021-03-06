package mma.entities.compByAnuke;

import arc.util.io.*;
import mindustry.annotations.Annotations.*;
import mindustry.entities.*;
import mindustry.gen.*;
import static mindustry.Vars.*;
import static mindustry.logic.LAccess.*;

@Component
@BaseComponent
abstract class EntityComp {

    private transient boolean added;

    transient int id = EntityGroup.nextId();

    boolean isAdded() {
        return added;
    }

    void update() {
    }

    void remove() {
        added = false;
    }

    void add() {
        added = true;
    }

    boolean isLocal() {
        Unitc u;
        return ((Object) this) == player || (((Object) this) instanceof Unitc && (u = (Unitc) ((Object) this)) == ((Object) this)) && u.controller() == player;
    }

    boolean isRemote() {
        Unitc u;
        return (((Object) this) instanceof Unitc && (u = (Unitc) ((Object) this)) == ((Object) this)) && u.isPlayer() && !isLocal();
    }

    boolean isNull() {
        return false;
    }

    /**
     * Replaced with `this` after code generation.
     */
    <T extends Entityc> T self() {
        return (T) this;
    }

    <T> T as() {
        return (T) this;
    }

    @InternalImpl
    abstract int classId();

    @InternalImpl
    abstract boolean serialize();

    @MethodPriority(1)
    void read(Reads read) {
        afterRead();
    }

    void write(Writes write) {
    }

    void afterRead() {
    }
}
