package com.ravi.ecommerce.services;

import com.ravi.ecommerce.controllers.GiftCardController;
import com.ravi.ecommerce.exceptions.GiftCardDoesntExistException;
import com.ravi.ecommerce.exceptions.GiftCardExpiredException;
import com.ravi.ecommerce.models.GiftCard;
import com.ravi.ecommerce.models.LedgerEntry;
import com.ravi.ecommerce.models.TransactionType;
import com.ravi.ecommerce.repositories.GiftCardRepository;
import com.ravi.ecommerce.repositories.LedgerEntryRepository;
import com.ravi.ecommerce.utils.GiftCardUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Service
public class GiftCardServiceImpl implements GiftCardService{
    private GiftCardRepository giftCardRepository;
    private LedgerEntryRepository ledgerEntryRepository;
    private GiftCardUtils giftCardUtils;
    @Autowired
    public GiftCardServiceImpl(GiftCardRepository giftCardRepository, LedgerEntryRepository ledgerEntryRepository, GiftCardUtils giftCardUtils) {
        this.giftCardRepository = giftCardRepository;
        this.ledgerEntryRepository = ledgerEntryRepository;
        this.giftCardUtils = new GiftCardUtils();
    }
    @Override
    public GiftCard createGiftCard(double amount) {
        GiftCard giftCard = new GiftCard();
        giftCard.setAmount(amount);
        giftCard.setCreatedAt(new Date());
        // Get the current date
        Date now = new Date();

        // Create a Calendar instance and set it to the current date
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(now);

        // Add one year to the current date
        calendar.add(Calendar.YEAR, 1);

        // Get the updated date
        Date oneYearFromNow = calendar.getTime();
        giftCard.setExpiresAt(oneYearFromNow);
        giftCard.setGiftCardCode(GiftCardUtils.generateGiftCardCode());
        LedgerEntry ledgerEntry = new LedgerEntry();
        ledgerEntry.setAmount(amount);
        ledgerEntry.setCreatedAt(new Date());
        ledgerEntry.setTransactionType(TransactionType.CREDIT);
        ledgerEntry = ledgerEntryRepository.save(ledgerEntry);
        giftCard.setLedger(List.of(ledgerEntry));
        return giftCardRepository.save(giftCard);

    }

    @Override
    public GiftCard redeemGiftCard(int giftCardId, double amountToRedeem) throws GiftCardDoesntExistException, GiftCardExpiredException {
        GiftCard giftCard = giftCardRepository.findById(giftCardId).orElseThrow(()->new GiftCardDoesntExistException("Gift Card with id "+giftCardId+" does not exist"));
        if(giftCard.getExpiresAt().before(new Date())){
            throw new GiftCardExpiredException("Gift Card with id "+giftCardId+" has expired");
        }
        amountToRedeem = Math.min(amountToRedeem, giftCard.getAmount());
        LedgerEntry ledgerEntry = new LedgerEntry();
        ledgerEntry.setAmount(amountToRedeem);
        ledgerEntry.setCreatedAt(new Date());
        ledgerEntry.setTransactionType(TransactionType.DEBIT);
        ledgerEntry = ledgerEntryRepository.save(ledgerEntry);
        giftCard.getLedger().add(ledgerEntry);
        double remainingAmount = giftCard.getAmount() - amountToRedeem;
        giftCard.setAmount(remainingAmount);
        return giftCardRepository.save(giftCard);
    }
}
