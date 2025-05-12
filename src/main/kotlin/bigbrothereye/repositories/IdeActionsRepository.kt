package bigbrothereye.repositories

import bigbrothereye.entities.IdeAction

interface IdeActionsRepository {
    fun isEmpty(): Boolean
    fun get(limit: Int? = null): Iterable<IdeAction>
    fun add(ideAction: IdeAction)
    fun delete(ideAction: IdeAction)
    fun clear()
}
