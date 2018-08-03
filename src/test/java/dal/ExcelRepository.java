package dal;

import com.poiji.bind.Poiji;

import java.io.File;
import java.util.List;

public class ExcelRepository<T> {
    private final String file;
    private final Class<T> type;

    public ExcelRepository(final String file, Class<T> type) {
        this.file = file;
        this.type = type;
    }

    public List<T> getAll() {
        return Poiji.fromExcel(new File(file), type);
    }
}
