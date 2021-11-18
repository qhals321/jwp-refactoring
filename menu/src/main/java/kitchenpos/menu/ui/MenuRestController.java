package kitchenpos.menu.ui;

import kitchenpos.menu.application.MenuService;
import kitchenpos.menu.ui.request.MenuCreateRequest;
import kitchenpos.menu.application.response.MenuResponse;
import kitchenpos.menu.ui.request.MenuUpdateRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.util.List;

@RestController
public class MenuRestController {
    private final MenuService menuService;

    public MenuRestController(final MenuService menuService) {
        this.menuService = menuService;
    }

    @PostMapping("/api/menus")
    public ResponseEntity<MenuResponse> create(@RequestBody final MenuCreateRequest menu) {
        final MenuResponse response = menuService.create(menu.toEntity());
        final URI uri = URI.create("/api/menus/" + response.getId());
        return ResponseEntity.created(uri)
                .body(response);
    }

    @GetMapping("/api/menus")
    public ResponseEntity<List<MenuResponse>> list() {
        return ResponseEntity.ok()
                .body(menuService.list());
    }

    @PutMapping("/api/menus/{menuId}")
    public ResponseEntity<MenuResponse> updateMenu(@PathVariable Long menuId,
                                                   @RequestBody MenuUpdateRequest menuUpdateRequest) {
        final MenuResponse menuResponse = menuService
            .updateMenuInfo(menuId, menuUpdateRequest.getName(), menuUpdateRequest.getPrice());
        return ResponseEntity.ok(menuResponse);
    }
}
