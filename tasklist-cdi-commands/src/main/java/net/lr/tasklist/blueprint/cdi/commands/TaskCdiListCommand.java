package net.lr.tasklist.blueprint.cdi.commands;

import javax.inject.Inject;
import javax.inject.Singleton;

import org.apache.karaf.shell.table.ShellTable;
import org.ops4j.pax.cdi.api.OsgiService;
import org.ops4j.pax.cdi.api.OsgiServiceProvider;
import org.ops4j.pax.cdi.api.Properties;
import org.ops4j.pax.cdi.api.Property;

import net.lr.tasklist.blueprint.cdi.model.Task;
import net.lr.tasklist.blueprint.cdi.model.TaskService;

@Singleton
@OsgiServiceProvider(classes= {TaskCdiListCommand.class})
@Properties(//
{
 @Property(name = "osgi.command.scope", value = "taskcdi"),
 @Property(name = "osgi.command.function", value = "list")
})
public class TaskCdiListCommand {
	
	@Inject @OsgiService
	TaskService taskService;
	
	public void list() throws Exception {
        ShellTable table = new ShellTable();
        table.column("id");
        table.column("title");
        table.column("description");
        for (Task task : taskService.getTasks()) {
            table.addRow().addContent(task.getId(), task.getTitle(), task.getDescription());
        }
        table.print(System.out);
    }
}
