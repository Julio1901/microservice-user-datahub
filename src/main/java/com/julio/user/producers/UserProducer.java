package com.julio.user.producers;

import com.julio.user.dtos.EmailDto;
import com.julio.user.models.UserModel;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class UserProducer {

    final RabbitTemplate rabbitTemplate;

    public  UserProducer(RabbitTemplate rabbitTemplate){
        this.rabbitTemplate = rabbitTemplate;
    }

    @Value(value = "${broker.queue.email.name}")
    private String routingKey;

    public  void publishMessageEmail(UserModel userModel){
        var emailDTO = new EmailDto();
        emailDTO.setUserId(userModel.getUserId());
        emailDTO.setEmailTo(userModel.getEmail());
        emailDTO.setSubject("Cadastro realiado com sucesso!");
        emailDTO.setText(userModel.getName() + ", seja bem vindo");


        rabbitTemplate.convertAndSend("", routingKey, emailDTO);

    }

}
