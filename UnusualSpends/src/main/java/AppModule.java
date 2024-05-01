import com.amaap.unusualspends.repository.CreditCardRepository;
import com.amaap.unusualspends.repository.impl.InMemoryCreditCardRepository;
import com.google.inject.AbstractModule;

public class AppModule extends AbstractModule {
    @Override
    protected void configure() {
        bind(CreditCardRepository.class).to(InMemoryCreditCardRepository.class);
    }
}