package com.inventatrack.platform.inventory.interfaces.rest.transform;

import com.inventatrack.platform.inventory.domain.model.commands.CreateInventoryCommand;
import com.inventatrack.platform.inventory.interfaces.rest.resources.CreateInventoryResource;

public class CreateInventoryCommandFromResourceAssembler {

    public static CreateInventoryCommand toCommand(CreateInventoryResource resource) {
        return new CreateInventoryCommand(
                resource.month(),
                resource.products()
        );
    }
}
