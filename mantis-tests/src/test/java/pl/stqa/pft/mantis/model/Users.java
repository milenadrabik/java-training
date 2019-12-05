package pl.stqa.pft.mantis.model;

import com.google.common.collect.ForwardingSet;

import java.util.List;
import java.util.Set;
import java.util.stream.Stream;

public class Users extends ForwardingSet<UserData> {

    public Users(List<UserData> result) {
    }

    @Override
    protected Set<UserData> delegate() {
        return null;
    }

    @Override
    public Stream<UserData> stream() {
        return null;
    }
}
