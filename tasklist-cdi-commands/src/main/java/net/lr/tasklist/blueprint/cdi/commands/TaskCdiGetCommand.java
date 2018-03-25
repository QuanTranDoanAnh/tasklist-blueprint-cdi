package net.lr.tasklist.blueprint.cdi.commands;

import javax.inject.Inject;
import javax.inject.Singleton;

import org.ops4j.pax.cdi.api.OsgiService;
import org.ops4j.pax.cdi.api.OsgiServiceProvider;
import org.ops4j.pax.cdi.api.Properties;
import org.ops4j.pax.cdi.api.Property;

import net.lr.tasklist.blueprint.cdi.model.Task;
import net.lr.tasklist.blueprint.cdi.model.TaskService;

@Singleton
@OsgiServiceProvider(classes= {TaskCdiGetCommand.class})
@Properties(//
{
 @Property(name = "osgi.command.scope", value = "taskcdi"),
 @Property(name = "osgi.command.function", value = "get")
})
public class TaskCdiGetCommand {
	@Inject @OsgiService
    TaskService taskService;

    public Task get(Integer id) throws Exception {
        return taskService.getTask(id);
    }
}
