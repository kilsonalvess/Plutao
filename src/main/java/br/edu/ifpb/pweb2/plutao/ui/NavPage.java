package br.edu.ifpb.pweb2.plutao.ui;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Component
public class NavPage {
    private int currentPage;
    private long totalItems;
    private int totalPages;
    private int pageSize;

}

