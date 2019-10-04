package dk.kea.class2019january.mathiasg.chargefinder;

public interface RepoCallback<T>
{
    void onSuccess(T result);
    void onFailure();
}
