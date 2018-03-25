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
@OsgiServiceProvider(classes= {TaskCdiAddCommand.class})
@Properties(//
{
 @Property(name = "osgi.command.scope", value = "taskcdi"),
 @Property(name = "osgi.command.function", value = "add")
})
public class TaskCdiAddCommand {
	@Inject @OsgiService
	TaskService taskService;
	
	public void add(Integer id, String title, String description, boolean finished) throws Exception {
		Task task = new Task();
        task.setId(id);
        task.setTitle(title);
        task.setDescription(description);
        task.setFinished(finished);
        taskService.addTask(task);
	}
}
