package jpashop.shop.dto.pagination;

import lombok.Getter;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

@Getter
public class PageVo {

    private static final int default_size = 10;
    private static final int default_max_size = 50;

    private int page;
    private int size;
    private String type;
    private String keyword;

    public PageVo() {
        this.page = 1;
        this.size = default_size;
    }

    public void setPage(int page) {
        this.page = page <= 0 ? 1 : page;
    }

    public void setSize(int size) {
        this.size = size < default_size || size > default_max_size ? default_size : size;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }


    public Pageable makePageable(int direction, String probs) {
        Sort.Direction dir = Sort.Direction.DESC;
        dir = direction == 0 ? Sort.Direction.DESC : Sort.Direction.ASC;

        return PageRequest.of(this.page - 1, this.size, dir, probs);
    }

}
