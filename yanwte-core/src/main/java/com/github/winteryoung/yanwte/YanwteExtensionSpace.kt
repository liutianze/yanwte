package com.github.winteryoung.yanwte

import java.util.*

/**
 * Place a subclass of this in your extension space package. This is optional.
 * But this can help you describe what the friend extension spaces are.
 * Extensions of the friend extension spaces can access the data extensions
 * of this extension space.
 *
 * @author Winter Young
 * @since 2016/1/26
 */
abstract class YanwteExtensionSpace {
    /**
     * Implement this method to invoke [addFriendExtSpace] to describe
     * what the friend extension spaces are.
     */
    protected abstract fun initializeImpl()

    /**
     * Initialize this extension space with [initializeImpl] called and
     * the name set.
     */
    internal fun initialize(name: String) {
        this._name = name
        initializeImpl()
        friends.add(name)
    }

    private var _name: String? = null
    /**
     * The name of this extension space.
     */
    val name: String
        get() = _name!!

    /**
     * Whether to control friend access. The default is true.
     */
    protected var controlFriendAccess = true

    /**
     * The friend list.
     */
    internal val friends: ArrayList<String> = ArrayList()

    /**
     * Is the given extension space authorized to access this.
     * An extension space is always authorized by itself.
     */
    fun isAuthorizedTo(extensionSpaceName: String): Boolean {
        return controlFriendAccess.not() || friends.contains(extensionSpaceName)
    }

    /**
     * Add the given friend extension space as a friend of this extension space.
     */
    protected fun addFriendExtSpace(extensionSpaceName: String) {
        friends.add(extensionSpaceName)
    }

    /**
     * Add the given friend extension space as a friend of this extension space.
     */
    protected fun addFriendExtSpace(extensionSpace: Package) {
        addFriendExtSpace(extensionSpace.name)
    }
}
