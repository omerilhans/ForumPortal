
package com.forum.repository;

import com.forum.database.BaseRepository;
import com.forum.entity.Degree;

public class DegreeRepository extends BaseRepository<Degree> {

    public DegreeRepository() {
        super(Degree.class);
    }

}
